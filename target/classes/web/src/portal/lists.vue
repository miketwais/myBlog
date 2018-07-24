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
            <BreadcrumbItem>{{removedot(sortNames)}}</BreadcrumbItem>
        </Breadcrumb>
        <div style="margin-top:20px;">
            <ul>
                <li v-for="item in articleLists" >
                    <div clsaa="artContent" style="padding: 10px 0;">
                        <router-link :to="getArticleUrl(item.id)"><h1 class="articleTitle">{{ item.title }}</h1></router-link>
                        <p><Icon type="android-create"></Icon>&nbsp;创建日期：<span class="artContent_tips">{{ item.create_by }}</span>&nbsp;&nbsp;&nbsp;&nbsp;<Icon type="eye"></Icon>&nbsp;浏览量:<span class="artContent_tips">{{ item.traffic }}</span></p>
                        <p>简介：<span class="artContent_summrry">{{ item.summary }}</span></p>
                        <p>正文：<span class="artContent_cont">{{splitContent(item.content)}}</span><router-link :to="getArticleUrl(item.id)">阅读全文</router-link></p>
                    </div>
                </li>
                <li>
                    <div style="text-align: right;">
                        <Page :total="total" :page-size="pageInfo.pageSize" show-elevator show-total @on-change="e=>{pageSearch(e)}"></Page>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</template>
<script>
    import { Carousel, Slide } from 'vue-carousel';
    export default {
        data(){
            return {
                value2:0,
                total:0,
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
                articleLists:[],
                sortNames:''

            }
        },
        components: {
            Carousel,
            Slide
        },
        mounted(){
            /*页面初始化调用方法*/
            this.getListsBySortId(this.$route.params.id);
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
            initPageInfo() {
                this.pageInfo.page = 0;
                this.pageInfo.pageSize = 10;
            },
            getListsBySortId(e){
                this.axios({
                    method: 'get',
                    url: '/api/artical/listBySortId/'+e,
                    params: {
                        'page':this.pageInfo.page,
                        'pageSize':this.pageInfo.pageSize,
                    }
                }).then(function (response) {
                    this.articleLists = response.data.data;
                    this.sortNames=response.data.data[0].sortNames;
                    this.total = response.data.totalCount;
                    console.log(this.articleLists);
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
            },
            splitContent(s){
                var dd=s.replace(/<\/?.+?>/g,"");
                var dds=dd.replace(/ /g,"");//dds为得到后的内容
                var res = dds.substr(0,100);
                return res+'...';
            },
            getArticleUrl(id){
                return '/article/'+id;
            },
            removedot(s){
                var rStr = s.substr(s.length-1,s.length);
                if(rStr==','){
                    return s.substr(0,s.length-1);
                }else {
                    return s;
                }
            },
            formatContentImgUrl(str){
                // //var str = "this is test string <img src=\"http:yourweb.com/test.jpg\" width='50' > 123 and the end <img src=\"所有地址也能匹配.jpg\" /> 33! <img src=\"/uploads/attached/image/20120426/20120426225658_92565.png\" alt=\"\" />"
                //匹配图片（g表示匹配所有结果i表示区分大小写）
                var res;
                if(str!=''&&str!=null){

                    // var imgReg = /<img.*?(?:>|\/>)/gi;
                    // //匹配src属性
                    // var srcReg = /src=[\'\"]?([^\'\"]*)[\'\"]?/i;
                    // var arr = str.match(imgReg);
                    // alert('所有已成功匹配图片的数组：'+arr);
                    // for (var i = 0; i < arr.length; i++) {
                    //     var src = arr[i].match(srcReg);
                    //     //获取图片地址
                    //     if(src[1]&&(src[1].indexOf('http')==-1)){//不包含http(内部链接)
                    //         alert('已匹配的图片地址'+(i+1)+'：'+src[1]);
                    //     }
                    //
                    // }
                    res = str.replace(/\.\.\//g,'../../');
                    return res;
                }else{
                    return str;
                }


            }
        }
    };
</script>