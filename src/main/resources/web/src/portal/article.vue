<style scoped>
    .layout{
        border: 1px solid #d7dde4;
        background: #f5f7f9;
        position: relative;
        border-radius: 4px;
        overflow: hidden;
    }
    .layout-logo{
        width: 100px;
        height: 30px;
        background: #5b6270;
        border-radius: 3px;
        float: left;
        position: relative;
        top: 15px;
        left: 20px;
    }
    .layout-nav{
        width: 420px;
        margin: 0 auto;
        margin-right: 20px;
    }
    .layout-footer-center{
        text-align: center;
    }
    /*slider*/
    #index_slide{
        margin-top:10px;
    }
    div ul li{
        list-style-type: none;
    }
    .artContent span .articleTitle{
        font-family: "Helvetica Neue";
        font-size: 16px;
    }
    .artContent p{
        font-family: "Helvetica Neue";
        font-size: 14px;
    }
</style>
<template>
    <div>
        <Breadcrumb :style="{margin: '16px 0'}">
            <BreadcrumbItem><router-link :to="String('/index')">Home</router-link></BreadcrumbItem>
            <BreadcrumbItem>{{articleDrtails.sortNames}}</BreadcrumbItem>
        </Breadcrumb>
        <div style="margin-top:20px;">
            <h1>{{articleDrtails.title}}</h1>
            <p style="margin: 20px;">&nbsp;&nbsp;创建时间:{{articleDrtails.create_by}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;浏览量：{{articleDrtails.traffic}}</p>
            <div v-html="formatContentImgUrl(articleDrtails.content)" :style="{padding: '20px'}">
            </div>
        </div>

        <!--评论区-->
        <div style="margin-top:20px;border-top:1px solid #eee;">
            <!--评论列表展示-->
            <h2>评论&留言</h2>
            <ul style="margin: 20px 0;">
                <li v-for="item in messageLists">
                    <p>姓名:&nbsp;{{item.name}}&nbsp;&nbsp;Email:&nbsp;{{item.email}}&nbsp;&nbsp;Ip:&nbsp;{{item.ip}}</p>
                    <div style="padding:5px;border:1px solid #eee;">评论内容：<br />
                    {{item.content}}
                    </div>
                </li>
                <div style="text-align: right;">
                    <Page :total="total" size="small" show-total :page-size="pageInfo.pageSize" @on-change="e=>{pageSearch(e)}"></Page>
                </div>
            </ul>
            <!--评论功能-->
            <div>
                <h2>请留言</h2>
                <Form ref="MessageNew" :model="MessageNew" :rules="ruleMessageNew" :label-width="80" >
                    <Row>
                        <Col span="10">
                        <Form-item label="Email:" prop="email">
                            <input type="hidden" v-model="MessageNew.article_id">
                            <Input v-model="MessageNew.email" />
                        </Form-item>
                        </Col>
                        <Col span="10">
                        <Form-item label="姓名:" prop="name">
                            <Input v-model="MessageNew.name" />
                        </Form-item>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="18">
                        <Form-item label="留言:" prop="content">
                            <Input v-model="MessageNew.content" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="Enter something..."></Input>
                        </Form-item>
                        </Col>
                    </Row>
                    <Row>
                        <FormItem>
                            <Button type="primary" @click="messageSubmit('MessageNew')">提交</Button>
                        </FormItem>
                    </Row>
                </Form>
            </div>
            </div>
        </div>
    </div>
</template>
<script>
    import { Carousel, Slide } from 'vue-carousel';
    export default {
        data(){
            return {
                localIp:'',
                article_id:this.$route.params.id,
                total:0,
                MessageNew:{
                    article_id:'',
                    email:'',
                    name:'',
                    content:''
                },
                /*新建验证*/
                ruleMessageNew: {
                    name: [
                        {type: 'string', required: true, message: '输入名称', trigger: 'blur'}
                    ],
                    email: [
                        {type: 'string', required: true, message: '输入邮箱', trigger: 'blur'},
                        { type: 'email', message: '邮箱格式不正确', trigger: 'blur' }
                    ],
                    content: [
                        {type: 'string', required: true, message: '输入留言内容', trigger: 'blur'}
                    ],
                    sortId: [
                        {type: 'Int', required: true, message: '请选择所属分类', trigger: 'blur'}
                    ]
                },
                value2:0,
                bannerList:[
                    {
                        id:1,
                        url:'http://www.baidu.com',
                        imagSrc:require('../images/1.jpg')
                    },
                    {
                        id:2,
                        url:'http://www.baidu.com',
                        imagSrc:require('../images/2.jpg')
                    },
                    {
                        id:3,
                        url:'http://www.baidu.com',
                        imagSrc:require('../images/3.jpg')
                    }
                ],
                //articleDrtails:[],
                messageLists:[],
                sortLists:[],
                articleDrtails:{
                    id:'',
                    title:'',
                    summary:'',
                    create_by:'',
                    content:'',
                    sortNames:'',
                    traffic:''
                },
                /*pageInfo实体*/
                pageInfo:{
                    page:0,
                    pageSize:100
                },

            }
        },
        components: {
            Carousel,
            Slide
        },
        mounted(){
            /*页面初始化调用方法*/
            this.getArticleDetails(this.$route.params.id);
            this.getSortLists();
            this.getMessags(this.$route.params.id);
            this.MessageNew.article_id = this.$route.params.id;
            //刷新一次执行一次
            this.updateTraffic(this.$route.params.id);
        },
        methods: {
            articleDetailSet(e){
                this.articleDrtails.id = e.id;
                this.articleDrtails.title = e.title;
                this.articleDrtails.summary = e.summary;
                this.articleDrtails.create_by = e.create_by;
                this.articleDrtails.sortNames = e.sortNames;
                this.articleDrtails.traffic = e.traffic;
                this.articleDrtails.content = e.content;

            },
            initMessageNew(){
              this.MessageNew.article_id =null;
              this.MessageNew.content = null;
              this.MessageNew.email = null;
              this.MessageNew.name = null;
            },
            initPageInfo() {
                this.pageInfo.page = 0;
                this.pageInfo.pageSize = 10;
            },
            //刷新浏览量
            updateTraffic(e){
                this.axios({
                    method: 'put',
                    url: '/api/artical/updateTraffic/'+e,
                    params: {
                    }
                }).then(function (response) {
                    this.articleDrtails.traffic= response.data;
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
            },
            //
            /*获取文章评论*/
            getMessags(e) {
                this.axios({
                    method: 'get',
                    url: '/api/message/list/'+e,
                    params: {
                        'page': this.pageInfo.page,
                        'pageSize': 10,
                    }
                }).then(function (response) {
                    this.messageLists = response.data.data;
                    this.total = response.data.totalCount;
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
            },
            getSortLists(){
                this.axios({
                    method: 'get',
                    url: '/api/sort/list/',
                    params: {
                        'page':this.pageInfo.page,
                        'pageSize':this.pageInfo.pageSize,
                        'catName':''
                    }
                }).then(function (response) {
                    this.sortLists = response.data.data;
                    console.log(this.sortLists);
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
            },
            getArticleDetails(e) {
                this.axios({
                    method: 'get',
                    url: '/api/artical/details/'+e,
                    params: {
                    }
                }).then(function (response) {
                    this.articleDetailSet(response.data);
                    console.log(this.articleDrtails);
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
            },
            getArticleUrl(id){
                return 'article/'+id;
            },
            formatContentImgUrl(str){
                // //var str = "this is test string <img src=\"http:yourweb.com/test.jpg\" width='50' > 123 and the end <img src=\"所有地址也能匹配.jpg\" /> 33! <img src=\"/uploads/attached/image/20120426/20120426225658_92565.png\" alt=\"\" />"
                //匹配图片（g表示匹配所有结果i表示区分大小写）
                var res;
                if(str!=''&&str!=null){
                    res = str.replace(/\.\.\//g,'../../');
                    return res;
                }else{
                    return str;
                }
            },
            //提交评论
            messageSubmit(message){
                this.axios({
                    method: 'post',
                    url: '/api/message/add/',
                    data:this.MessageNew,
                }).then(function (response) {
                    this.initMessageNew();
                    //this.$Message.info('评论成功');
                    this.getMessags(this.$route.params.id);
                    this.$Message.info(response.data);
                }.bind(this)).catch(function (error) {
                    this.initMessageNew();
                    this.$Message.info('评论失败');
                    //alert(error);
                });
            }
        }
    };
</script>