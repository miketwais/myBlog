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
        <BreadcrumbItem><router-link :to="String('/about')">about</router-link></BreadcrumbItem>
    </Breadcrumb>
    <div style="margin-top:20px;">
        <div style="top: 10px;background: none;">
            <router-link :to="String('/index')">
                <img src="../images/logo.png" height="62px">
            </router-link>
        </div>
        <ul style="margin-top:20px">
            <li >
                <h2>职业：工程师</h2>
            </li>
            <li>
                <h2>联系方式：www.github.com/miketwais</h2>
            </li>
            <li>
                <h2>博客园：www.cnblogs.con/miketwais</h2>
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
                /*pageInfo实体*/
                pageInfo: {
                    page: 0,
                    pageSize: 100
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
                articleLists:[],
                sortLists:[],
                total:0

            }
        },
        components: {
            Carousel,
            Slide
        },
        mounted(){
            /*页面初始化调用方法*/

            this.getSortLists();
        },
        methods: {
            initPageInfo() {
                this.pageInfo.page = 0;
                this.pageInfo.pageSize = 10;
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
            //
            /*得到表数据*/
            getArticle(e) {
                this.axios({
                    method: 'get',
                    url: '/api/artical/list',
                    params: {
                        'page': e.pageInfo.page,
                        'pageSize': e.pageInfo.pageSize,
                        'articleName': e.articleName
                    }
                }).then(function (response) {
                    this.articleLists = response.data.data;
                    this.total = response.data.totalCount;
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
            }
        }
    };
</script>