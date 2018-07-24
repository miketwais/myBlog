<style scoped>
    ul li{
        list-style-type: none;
    }
</style>
<template>
	<div style="margin: 20px;">
        <div>
            <Row style="margin-bottom: 25px;">
                <Col span="8">文章名称：
                	<Input v-model="articleName" placeholder="请输入..." style="width:200px"></Input>
                </Col>
                <Col span="8"><Button type="primary" shape="circle" icon="ios-search" @click="search()">搜索</Button></Col>
            </Row>
        </div>
        <div>
            <ul>
                <li>
                    <Button type="primary" icon="plus-round" @click="openNewModal()">新建</Button>
                    <Button type="success" icon="wrench" @click="openModifyModal()">修改</Button>
                    <Button type="error" icon="trash-a" @click="del()">删除</Button>
                </li>
                <li>
                    <div style="padding: 10px 0;">
                    	<Table border :columns="columns1" :data="data1" :height="400" @on-selection-change="s=>{change(s)}" ></Table>
                    </div>
                </li>
                <li>
                    <div style="text-align: right;">
                        <Page :total="total" :page-size="pageInfo.pageSize" show-elevator show-total @on-change="e=>{pageSearch(e)}"></Page>
                    </div>
                </li>
            </ul>
        </div>
        <!--添加modal-->
        <Modal :mask-closable="false" :visible.sync="newModal" :loading = "loading" v-model="newModal" width="900" title="新建" @on-ok="newOk('articleNew')" @on-cancel="cancel()">
            <Form ref="articleNew" :model="articleNew" :rules="ruleNew" :label-width="80" >
                <Row>
                    <Col span="16">
                        <Form-item label="文章名:" prop="title">
                            <Input v-model="articleNew.title" />
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item label="激活文章" prop="active">
                            <i-switch v-model="articleNew.is_effective" size="large" @on-change="test" true-value="1" false-value="0">
                                <span slot="open">On</span>
                                <span slot="close">Off</span>
                            </i-switch>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="16">
                    <Form-item label="描述" prop="summary">
                        <Input v-model="articleNew.summary" type="textarea" :rows="4" placeholder="Enter summary..." />
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                    <Form-item label="所属分类" prop="sortsList">
                        <Select v-model="sortsListModel" multiple style="width:200px" @on-change="selectSortId(sortsListModel)">
                            <Option v-for="item in sortsList" :value="String(item.id)" :key="String(item.id)">{{ item.name }}</Option>
                        </Select>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                    <Form-item label="置顶" prop="is_top">
                        <i-switch  v-model="articleNew.is_top" size="large" @on-change="test" true-value="1" false-value="0">
                            <span slot="open">On</span>
                            <span slot="close">Off</span>
                        </i-switch>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="20">
                    <Form-item label="文章内容" prop="content">
                        <div class="app-container calendar-list-container">
                        <div>
                        <editor class="editor" :value="content" :setting="editorSetting" @show="editors" :url="Url"
                        :max-size= "MaxSize"
                        :accept= "Accept"
                        :with-credentials= "withCredentials"
                        @on-upload-fail= "onEditorReady"
                        @on-upload-success= "onEditorUploadComplete"></editor>
                        </div>
                        </div>
                    </Form-item>
                    </Col>
                </Row>
            </Form>
        </Modal>
        <!--修改modal-->
        <Modal :mask-closable="false" :visible.sync="modifyModal" :loading = "loading" v-model="modifyModal" width="900" title="修改" @on-ok="modifyOk('articleModify')" @on-cancel="cancel()">
             <Form ref="articleModify" :model="articleModify" :rules="ruleModify" :label-width="80" >
                 <Row>
                     <Col span="16">
                     <Form-item label="文章名:" prop="title">
                         <Input v-model="articleModify.title" />
                     </Form-item>
                     </Col>
                 </Row>
                 <Row>
                     <Col span="12">
                     <Form-item label="激活文章" prop="active">
                         <i-switch v-model="articleModify.is_effective" size="large">
                             <span slot="open">On</span>
                             <span slot="close">Off</span>
                         </i-switch>
                     </Form-item>
                     </Col>
                 </Row>
                 <Row>
                     <Col span="16">
                     <Form-item label="描述" prop="summary">
                         <Input v-model="articleModify.summary" type="textarea" :rows="4" placeholder="Enter summary..." />
                     </Form-item>
                     </Col>
                 </Row>
                 <Row>
                     <Col span="12">
                     <Form-item label="所属分类" prop="sortsList">
                         <Select v-model="sortNames1" multiple style="width:260px" @on-change="selectSortId(sortsListModel)">
                             <Option v-for="item in sortsList" :value="String(item.id)" :key="String(item.id)">{{ item.name }}</Option>
                         </Select>
                     </Form-item>
                     </Col>
                 </Row>
                 <Row>
                     <Col span="12">
                     <Form-item label="置顶" prop="is_top">
                         <i-switch  v-model="articleModify.is_top" size="large">
                             <span slot="open">On</span>
                             <span slot="close">Off</span>
                         </i-switch>
                     </Form-item>
                     </Col>
                 </Row>
                 <Row>
                     <Col span="20">
                     <Form-item label="文章内容" prop="content">
                         <div class="app-container calendar-list-container">
                             <div>
                                 <editor class="editor" :value="String(articleModify.content)" :setting="editorSetting" @show="editors" :url="Url"
                                         :max-size= "MaxSize"
                                         :accept= "Accept"
                                         :with-credentials= "withCredentials"
                                         @on-upload-fail= "onEditorReady"
                                         @on-upload-success= "onEditorUploadComplete"></editor>
                             </div>
                         </div>
                     </Form-item>
                     </Col>
                 </Row>
            </Form>
        </Modal>
        <!--messageLists-->
        <Modal :mask-closable="false" :visible.sync="messageModal" :loading = "loading" v-model="messageModal" width="900" title="评论列表" @on-ok="newOk('articleNew')" @on-cancel="cancel()">
            <ul>
                <li>
                    <Button type="error" icon="trash-a" @click="messageDel()">删除</Button>
                </li>
            <li>
                <div style="padding: 10px 0;">
                    <Table border :columns="messageColumns" :data="messageLists" :height="400" @on-selection-change="s=>{mesTblChange(s)}" ></Table>
                </div>
            </li>
            <li>
                <div style="text-align: right;">
                    <Page :total="total" :page-size="pageInfo.pageSize" show-elevator show-total @on-change="e=>{pageSearch(e)}"></Page>
                </div>
            </li>
            </ul>
        </Modal>
    </div>
</template>
<script>
    import editor from './editor' // 根据editor.vue组件位置引入
	export default {
        data() {
            return {
                editorSetting: { // 配置富文本编辑器高
                    height: 300
                },
                Url: 'http://127.0.0.1:8091/wh/api/upload/singleUpload', // 图片对应的上传地址
                MaxSize: 75765, // 文件大小
                Accept: 'image/jpeg, image/png', // 文件格式
                withCredentials: true,
                content: '', // 富文本编辑器双向绑定的内容
                /*用于查找的登录名*/
                articleName: null,
                /*选择的数量*/
                count: null,
                /*选中的组数据*/
                groupId: null,
                /*新建modal的显示参数*/
                newModal: false,
                /*修改modal的显示参数*/
                modifyModal: false,
                messageModal:false,
                /*角色配置modal的显示参数*/
                roleModal: false,
                /*分页total属性绑定值*/
                total: 0,
                /*loading*/
                loading: true,
                /*pageInfo实体*/
                pageInfo: {
                    page: 0,
                    pageSize: 10
                },
                /*user实体*/
                article: {
                    id: null,
                    title: null,
                    summary: null,
                    sortId: null,
                    content: null,
                    create_by: null,
                    modified_by: null,
                    is_top: 0,
                    is_effective: 0
                },
                /*用于添加的article实体*/
                articleNew: {
                    id: null,
                    title: null,
                    summary: null,
                    traffic: null,
                    sortId: null,
                    content: null,
                    create_by: null,
                    modified_by: null,
                    is_top: 0,
                    is_effective: 0
                },
                /*用于修改的user实体*/
                articleModify: {
                    id: null,
                    title: null,
                    summary: null,
                    traffic: null,
                    sortId: null,
                    content: null,
                    create_by: null,
                    modified_by: null,
                    is_top: null,
                    is_effective: null
                },
                /*新建验证*/
                ruleNew: {
                    name: [
                        {type: 'string', required: true, message: '输入文章名称', trigger: 'blur'}
                    ],
                    summary: [
                        {type: 'string', required: true, message: '输入描述', trigger: 'blur'}
                    ],
                    content: [
                        {type: 'string', required: true, message: '输入文章内容', trigger: 'blur'}
                    ],
                    sortId: [
                        {type: 'Int', required: true, message: '请选择所属分类', trigger: 'blur'}
                    ]
                },
                /*修改验证*/
                ruleModify: {
                    name: [
                        {type: 'string', required: true, message: '输入文章名称', trigger: 'blur'}
                    ],
                    summary: [
                        {type: 'string', required: true, message: '输入描述', trigger: 'blur'}
                    ],
                    content: [
                        {type: 'string', required: true, message: '输入文章内容', trigger: 'blur'}
                    ],
                    sortId: [
                        {type: 'Int', required: true, message: '请选择所属分类', trigger: 'blur'}
                    ]
                },
                messageColumns:[
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '姓名',
                        width: 100,
                        key: "name"
                    },
                    {
                        title: 'Email',
                        key: 'email'
                    },
                    {
                        title: '时间',
                        key: 'create_by'
                    },
                    {
                        title: '内容',
                        key: 'content'
                    },
                    {
                        title: '状态',
                        align: 'center',
                        render: (h, params) => {
                            return h('div',[
                                h('i-switch',{
                                    attrs:{
                                        'value' : params.row.is_effective
                                    },
                                    on:{
                                        'on-change':(val)=>{
                                            // var i = this.moduleArr.indexOf(params.row.id+'');
                                            // if(val){
                                            //     if(i == -1){
                                            //         this.moduleArr.push(params.row.id+'');
                                            //     }
                                            // }else{
                                            //     if(i != -1){
                                            //         this.moduleArr.splice(i,1);
                                            //     }
                                            // }
                                            console.log(val);
                                            console.log(params.row);
                                            this.updateMessageStatue(params.row.message_id,val);
                                        }
                                    }
                                })
                            ]);
                        }
                    },
                ],
                /*表显示字段*/
                columns1: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '文章名',
                        key: "title"
                    },
                    {
                        title: '所属分类',
                        key: 'sortNames'
                    },
                    {
                        title: '修改时间',
                        key: 'modified_by'
                    },
                    {
                        title: '状态（激活）',
                        key: 'articalInfo.is_effective',
                        render: (h, params) => {
                            return h('span', {attrs: {}}, this.reversActive(params.row.is_effective))
                        }
                    },
                    {
                        title: '操作',
                        align: 'center',
                        key: 'action',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'info',
                                    },
                                    style:{
                                        marginRight:'5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.openLookModifyModal(params.row);
                                        }
                                    }
                                }, '查看'),
                                h('Button', {
                                    props: {
                                        type: 'info',
                                    },
                                    on: {
                                        click: () => {
                                            this.openLookMessageModal(params.row);
                                        }
                                    }
                                }, '评论')
                            ]);
                        }
                    },
                ],
                /*表数据*/
                data1: [],
                /*表显示字段*/
                columns2: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '角色名称',
                        width: 120,
                        key: 'name'
                    },
                    {
                        title: '描述',
                        key: 'describe'
                    }
                ],
                /*表数据*/
                data2: [],
                /*data2的临时存储*/
                data2Temp: [],
                sortsList: [],
                //articleModify.sortId:['1','2'],
                sortNames1: [],
                sortsListModel: '',
                messageLists: [],
                /*临时状态的数组*/
                moduleArr:[],
                /*用户与角色关系列表*/
                relationList: null,
                //当前选择的文章ID（对应评论列表）
                curArticle_Id:''
            }
        },
        components: { // 引入组件
            editor
        },
        mounted() {
            /*页面初始化调用方法*/
            this.getTable({
                "pageInfo": this.pageInfo,
                "articleName": this.articleName
            });
            // this.axios({
            //   method: 'get',
            //   url: '/roles/all'
            // }).then(function (response) {
            //     this.data2Temp = response.data;
            // }.bind(this)).catch(function (error) {
            //   alert(error);
            // });
            //目录信息
            this.getSorts();
        },
        methods: {
            getSorts() {
                //目录信息
                this.axios({
                    method: 'get',
                    url: '/api/sort/allLists'
                }).then(function (response) {
                    this.sortsList = response.data;
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
            },
            editors(content) { // editor组件传过来的值赋给content
                console.log(content)
                this.articleNew.content = content
            },
            onEditorReady(ins, ina) { // 上传失败的函数
                console.log('ins')
                console.log(ins)
                console.log(ina)
            },
            onEditorUploadComplete(json) { // 处理上传图片后返回数据，添加img标签到编辑框内
                console.log('json')
                console.log(json)
                console.log(json[0].data.filePath)
                this.content = this.content + '&lt;img src=' + json[0].data.filePath + '&gt;'
            },

            test(val) {
                console.log(val);
                //this.catNew.is_effective=(val==true)?1:0;
                //console.log(this.articleNew);
            },
            selectSortId(value) {
                console.log(value);
                var res;
                //多选需要处理,若是多选,将数组转为字符串
                if (value.length > 1) {
                    res = value.join(',');
                } else {
                    res = value[0];
                }
                this.articleNew.sortId = res;
            },
            setModifySortId(value) {
                console.log(value);
                var res;
                //多选需要处理,若是多选,将数组转为字符串
                if (value.length > 1) {
                    res = value.join(',');
                } else {
                    res = value[0];
                }
                this.articleModify.sortId = res;
            },
            setDelIds(value) {
                console.log(value);
                var res;
                //多选需要处理,若是多选,将数组转为字符串
                if (value.length > 1) {
                    res = value.join(',');
                } else {
                    res = value[0];
                }
                return res;
            },
            /*pageInfo实体初始化*/
            initPageInfo() {
                this.pageInfo.page = 0;
                this.pageInfo.pageSize = 10;
            },
            initarticle() {
                this.article.id = null;
                this.article.title = null;
                this.article.summary = null;
                this.article.traffic = null,
                this.article.sortId = null;
                this.article.content = null;
                this.article.create_by = null;
                this.article.modified_by = null;
                this.article.is_top = false;
                this.article.is_effective = false;
            },
            /*articleNew实体初始化*/
            initarticleNew() {
                this.article.id = null;
                this.article.title = null;
                this.article.summary = null;
                this.article.traffic = null,
                this.article.sortId = null;
                this.article.content = null;
                this.article.create_by = null;
                this.article.modified_by = null;
                this.article.is_top = false;
                this.article.is_effective = false;
            },
            /*articleModify实体初始化*/
            initarticleModify() {
                this.articleModify.id = null;
                this.articleModify.title = null;
                this.articleModify.summary = null;
                this.articleModify.traffic = null,
                this.articleModify.sortId = null;
                this.articleModify.content = null;
                this.articleModify.create_by = null;
                this.articleModify.modified_by = null;
                this.articleModify.is_top = false;
                this.articleModify.is_effective = false;
            },
            /*article设置*/
            articleSet(e) {
                this.article.id = e.id;
                this.article.title = e.title;
                this.article.summary = e.summary;
                this.article.traffic = e.traffic;
                this.article.sortId = e.sortId;
                this.article.content = e.content;
                this.article.create_by = e.create_by;
                this.article.modified_by = e.modified_by;
                this.article.is_top = e.is_top ==1 ? true:false;
                this.article.is_effective = e.is_effective ==1 ? true:false;
            },
            /*articleNew设置*/
            articleNewSet(e) {
                this.article.id = e.id;
                this.article.title = e.title;
                this.article.summary = e.summary;
                this.article.traffic = e.traffic;
                this.article.sortId = e.sortId;
                this.article.content = e.content;
                this.article.create_by = e.create_by;
                this.article.modified_by = e.modified_by;
                this.article.is_top = e.is_top;
                this.article.is_effective = e.is_effective;
            },
            /*articleModify设置*/
            articleModifySet(e) {
                this.articleModify.id = e.id;
                this.articleModify.title = e.title;
                this.articleModify.summary = e.summary;
                this.articleModify.traffic = e.traffic;
                this.articleModify.sortId = e.sortId;
                this.articleModify.content = e.content;
                this.articleModify.create_by = e.create_by;
                this.articleModify.modified_by = e.modified_by;
                this.articleModify.is_top = e.is_top;
                this.articleModify.is_effective = e.is_effective;
            },
            /*得到表数据*/
            getTable(e) {
                this.axios({
                    method: 'get',
                    url: '/api/artical/list',
                    params: {
                        'page': e.pageInfo.page,
                        'pageSize': e.pageInfo.pageSize,
                        'articleName': e.articleName
                    }
                }).then(function (response) {
                    this.data1 = response.data.data;
                    this.total = response.data.totalCount;
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
            },
            /*搜索按钮点击事件*/
            search() {
                this.initPageInfo();
                this.getTable({
                    "pageInfo": this.pageInfo,
                    "articleName": this.articleName
                });
            },
            /*分页点击事件*/
            pageSearch(e) {
                this.pageInfo.page = e - 1;
                this.getTable({
                    "pageInfo": this.pageInfo,
                    "articleName": this.articleName
                });
            },
            /*新建点击触发事件*/
            openNewModal() {
                this.newModal = true;
                this.initarticleNew();
                this.data1.sort();
                this.count = 0;
                this.groupId = null;
            },
            /*新建modal的newOk点击事件*/
            newOk(articleNew) {
                this.$refs[articleNew].validate((valid) => {
                    if (valid) {
                        console.log(this.articleNew);
                        if (this.articleNew.title != null) {
                            this.initarticle();
                            this.articleSet(this.articleNew);
                            this.axios({
                                method: 'post',
                                url: 'api/artical/addArticle',
                                data: this.article
                            }).then(function (response) {
                                //this.initarticleNew();
                                this.getTable({
                                    "pageInfo": this.pageInfo,
                                    "articleName": this.articleName
                                });
                                this.$Message.info('新建成功');
                            }.bind(this)).catch(function (error) {
                                alert(error);
                            });
                            this.newModal = false;
                        } else {
                            this.$Message.error('请输入文章名称！');
                            this.loading = false;
                            this.$nextTick(() => {
                                this.loading = true;
                            });
                        }
                    } else {
                        this.$Message.error('表单验证失败!');
                        setTimeout(() => {
                            this.loading = false;
                            this.$nextTick(() => {
                                this.loading = true;
                            });
                        }, 1000);
                    }
                })
            },
            /*点击修改时判断是否只选择一个*/
            openModifyModal() {
                console.log(this.sortNames1);
                console.log(this.articleModify);
                if (this.count > 1 || this.count < 1) {
                    this.modifyModal = false;
                    this.$Message.warning('请至少选择一项(且只能选择一项)');
                } else {
                    this.modifyModal = true;
                    //this.articleModify.sortId = this.sortNames1;
                }
            },
            openLookModifyModal(e) {
                this.articleModifySet(e);
                this.modifyModal = true;
            },
            openLookMessageModal(e){
                this.curArticle_Id = e.id;
                this.getMessagesById(e.id);
                this.messageModal =true;
            },
            getMessagesById(e){
                this.axios({
                    method: 'get',
                    url: 'api/message/list/' + e,
                    params: {
                        'page': this.pageInfo.page,
                        'pageSize': this.pageInfo.pageSize,
                    }
                }).then(function (response) {
                    this.messageLists = response.data.data;
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
            },
            /*修改modal的modifyOk点击事件*/
            modifyOk(articleModify) {
                console.log(this.article);
                //delete this.article.content;
                this.setModifySortId(this.sortNames1);
                this.$refs[articleModify].validate((valid) => {
                    if (valid) {
                        this.initarticle();
                        this.articleSet(this.articleModify);
                        this.axios({
                            method: 'put',
                            url: 'api/artical/updateArticle/' + this.article.id,
                            data: this.article
                        }).then(function (response) {
                            //this.initarticleNew();
                            this.getTable({
                                "pageInfo": this.pageInfo,
                                "articleName": this.articleName
                            });
                            this.$Message.info('修改成功');
                            this.articleModify.sortId = null;
                        }.bind(this)).catch(function (error) {
                            alert(error);
                        });
                        this.modifyModal = false;
                    } else {
                        this.$Message.error('表单验证失败!');
                        setTimeout(() => {
                            this.loading = false;
                            this.$nextTick(() => {
                                this.loading = true;
                            });
                        }, 1000);
                    }
                })
            },
            /*modal的cancel点击事件*/
            cancel() {
                //this.sortNames1 = [];
                this.$Message.info('点击了取消');
                this.getTable({
                    "pageInfo": this.pageInfo,
                    "articleName": this.articleName
                });
            },
            /*table选择后触发事件*/
            change(e) {
                if (e.length == 1) {
                    this.articleModifySet(e['0']);
                    //select选中值赋值
                    //var selectList = [];
                    var sortIds = e['0'].sortIds;
                    if (sortIds.indexOf(',') != -1) {//,存在
                        var sortIdsArr = sortIds.split(',');
                        for (var i = 0; i < sortIdsArr.length; i++) {
                            this.sortNames1.push(sortIdsArr[i]);
                        }
                    } else {
                        this.sortNames1.push(sortIds);
                    }
                }else{
                    this.sortNames1=[];
                }
                console.log(this.sortNames1);
                this.setGroupId(e);
            },
            mesTblChange(e){
                // if (e.length == 1) {
                //     //select选中值赋值
                //     //var selectList = [];
                //     var sortIds = e['0'].sortIds;
                //     if (sortIds.indexOf(',') != -1) {//,存在
                //         var sortIdsArr = sortIds.split(',');
                //         for (var i = 0; i < sortIdsArr.length; i++) {
                //             this.sortNames1.push(sortIdsArr[i]);
                //         }
                //     } else {
                //         this.sortNames1.push(sortIds);
                //     }
                // }else{
                //     this.sortNames1=[];
                // }
                // console.log(this.sortNames1);
                this.setGroupId(e);
            },
            /*通过选中的行设置groupId的值*/
            setGroupId(e) {
                this.groupId = [];
                this.count = e.length;
                for (var i = 0; i <= e.length - 1; i++) {
                    this.groupId.push(e[i].id);
                }
            },
            /*删除table中选中的数据*/
            del() {
                if (this.groupId != null && this.groupId != "")
                    var delIds = this.setDelIds(this.groupId);
                this.axios({
                    method: 'delete',
                    url: 'api/artical/deleteArticles/',
                    //data: this.groupId
                    data:delIds
                }).then(function (response) {
                    this.getTable({
                        "pageInfo": this.pageInfo,
                        "articleName": this.articleName
                    });
                    this.groupId = null;
                    this.count = 0;
                    this.$Message.info('删除成功');
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
            },
            /*message del*/
            messageDel(){
                if (this.groupId != null && this.groupId != "")
                    var mesdelIds = this.setDelIds(this.groupId);
                this.axios({
                    method: 'delete',
                    url: 'api/message/deleteByIds/',
                    data:mesdelIds
                }).then(function (response) {
                    this.getMessagesById(this.curArticle_Id);
                    this.groupId = null;
                    this.count = 0;
                    this.$Message.info('删除成功');
                }.bind(this)).catch(function (error) {
                    alert(error);
                });
            },
        /*表格中双击事件*/
        // dblclick(e) {
        //     this.articleModifySet(e);
        //     this.modifyModal = true;
        //     this.data1.sort();
        // },
        reversActive(isActive) {
            if (isActive == true) {
                return "已激活";
            } else {
                return "未激活";
            }
            },
            //更新评论激活状态
        updateMessageStatue(id,val){
            this.axios({
                method: 'put',
                url: 'api/message/update/'+id+'/'+val,
                data:{}
            }).then(function (response) {
                this.$Message.info('更新状态成功');
            }.bind(this)).catch(function (error) {
                alert(error);
            });
            }
    }
    }
</script>