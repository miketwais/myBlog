<style type="text/css">
    .test{
        margin: 20px 20px 20px 20px;     
    }
    .test Button{
        margin-bottom: 10px;
    }
</style>
<template>
    <div class="test">
        <p style="font-size: 16px">关键字请用英文逗号隔开,示例如下:</p>
        <p style="font-size: 16px;margin-bottom:20px;">&nbsp;&nbsp;&nbsp;&nbsp;政府,社会</p>
        <Input v-model="keyWords" type="textarea" :rows="4" placeholder=""></Input>
        <Button type="warning" style="float:right;margin-top:20px;" @click="updateKeyWords()">保存</Button>
    </div>
</template>
<script>
    export default {
        data(){
            return {
                value: null,
                keyWords:''
            }
        },
        mounted(){
                this.getKeyWords();
        },
        methods: {
            getKeyWords(){
                this.axios({
                    /*headers: {'Authorization': 'bearer '+this.$store.state.users.currentUser.UserToken},*/
                    method: 'get',
                    url: '/api/keywords/getKeyWords',
                    data: {
                    }
                }).then(function(response){
                    this.keyWords= response.data;
                }.bind(this)).catch(function(error){
                    console.log(error);
                });
            },
            updateKeyWords(){
                //console.log(this.keyWords);
                this.axios({
                    /*headers: {'Authorization': 'bearer '+this.$store.state.users.currentUser.UserToken},*/
                    method: 'put',
                    url: '/api/keywords/updateKeyWords/'+this.keyWords,
                    data: {}
                }).then(function(response){
                    this.keyWords= response.data;
                }.bind(this)).catch(function(error){
                    console.log(error);
                });
            }
        }   
    };
</script>