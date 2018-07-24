<template>
	<div style="margin: 20px;">
        <div>
            <Row style="margin-bottom: 25px;">
                <Col span="8">目录名称：
                	<Input v-model="catName" placeholder="请输入..." style="width:200px"></Input>
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
                    	<Table border :columns="columns1" :data="data1" :height="400" @on-selection-change="s=>{change(s)}" @on-row-dblclick="s=>{dblclick(s)}"></Table>
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
        <Modal :mask-closable="false" :visible.sync="newModal" :loading = "loading" v-model="newModal" width="600" title="新建" @on-ok="newOk('catNew')" @on-cancel="cancel()">
            <Form ref="catNew" :model="catNew" :rules="ruleNew" :label-width="80" >
                <Row>
                    <Col span="12">
                        <Form-item label="目录名:" prop="name">
                            <Input v-model="catNew.name" style="width: 204px"/>
                        </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <Form-item label="激活目录" prop="active">
                            <i-switch v-model="catNew.isActive" size="large" @on-change="test" true-value="1" false-value="0">
                                <span slot="open">On</span>
                                <span slot="close">Off</span>
                            </i-switch>
                        </Form-item>
                    </Col>
                </Row>
            </Form>
        </Modal>
        <!--修改modal-->
        <Modal :mask-closable="false" :visible.sync="modifyModal" :loading = "loading" v-model="modifyModal" width="600" title="修改" @on-ok="modifyOk('catModify')" @on-cancel="cancel()">
             <Form ref="catModify" :model="catModify" :rules="ruleModify" :label-width="80" >
                 <Row>
                     <Col span="12">
                     <Form-item label="目录名:" prop="name">
                         <Input v-model="catModify.name" style="width: 204px"/>
                     </Form-item>
                     </Col>
                 </Row>
                 <Row>
                     <Col span="12">
                     <Form-item label="激活" prop="active">
                         <i-switch v-model="catModify.is_effective" size="large">
                             <span slot="open">On</span>
                             <span slot="close">Off</span>
                         </i-switch>
                     </Form-item>
                     </Col>
                 </Row>
            </Form>
        </Modal>
        <!--配置角色modal-->
        <Modal v-model="roleModal" width="500" title="角色配置" @on-ok="roleOk()" @on-cancel="cancel()">
            <div>
                <Table border :columns="columns2" :data="data2" :height="260"  @on-selection-change="s=>{change2(s)}"></Table>
            </div>
        </Modal>
    </div>
</template>
<script>
	export default {
        data () {
            return {
                /*用于查找的登录名*/
                catName:null,
            	/*选择的数量*/
                count:null,
            	/*选中的组数据*/
                groupId:null,
            	/*新建modal的显示参数*/
                newModal:false,
                /*修改modal的显示参数*/
                modifyModal:false,
                /*角色配置modal的显示参数*/
                roleModal:false,
            	/*分页total属性绑定值*/
                total:0,
                /*loading*/
                loading: true,
                /*pageInfo实体*/
                pageInfo:{
                	page:0,
                	pageSize:10
                },
                /*user实体*/
                cat:{
                    id:null,
                    name:null,
                    creatTime:null,
                    modifyTime:null,
                    is_effective:null
                },
                /*用于添加的cat实体*/
                catNew:{
                    id:null,
                    name:null,
                    creatTime:null,
                    modifyTime:null,
                    is_effective:null
                },
                /*用于修改的user实体*/
                catModify:{
                    id:null,
                    name:null,
                    creatTime:null,
                    modifyTime:null,
                    is_effective:null
                },
                /*新建验证*/
                ruleNew:{
                    name: [
                        { type:'string',required: true, message: '输入目录名', trigger: 'blur' }
                    ]
                },
                /*修改验证*/
                ruleModify:{
                    name: [
                        { type:'string',required: true, message: '输入目录名', trigger: 'blur' }
                    ]
                },
            	/*表显示字段*/
            	columns1: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '目录名',
                        key: 'name'
                    },
                    {
                        title: '创建时间',
                        key: 'create_by'
                    },
                    {
                        title: '修改时间',
                        key: 'modified_by'
                    },
                    {
                        title: '激活',
                        key: 'is_effective',
                        render: (h, params) =>{
                            return h('span', {attrs:{ }},this.reversActive(params.row.is_effective))
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
                                    on: {
                                        click: () => {
                                            this.openLookModifyModal(params.row);
                                        }
                                    }
                                },'查看')
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
                data2:[],
                /*data2的临时存储*/
                data2Temp:[],
                /*用户与角色关系列表*/
                relationList:null
            }
        },
        mounted(){
            //this.initFormatter();                  // 为Date 对象添加Format方法
        	/*页面初始化调用方法*/
            this.getTable({
                "pageInfo":this.pageInfo,
                "catName":this.catName
            });
            this.axios({
              method: 'get',
              url: '/roles/all'
            }).then(function (response) {
                this.data2Temp = response.data;
            }.bind(this)).catch(function (error) {
              alert(error);
            });
        },
        methods:{
            test (val) {
                console.log(val);
                //this.catNew.is_effective=(val==true)?1:0;
                console.log(this.catNew);
            },
        	/*pageInfo实体初始化*/
        	initPageInfo(){
        		this.pageInfo.page = 0;
        		this.pageInfo.pageSize = 10;
        	},
            /*cat实体初始化*/
            initCat(){
                this.cat.id = null;
                this.cat.name = null;
                this.cat.creatTime = null;
                this.cat.modifyTime = null;
                this.cat.is_effective = 0;
            },
            /*catNew实体初始化*/
            initCatNew(){
                this.catNew.id = null;
                this.catNew.name = null;
                this.catNew.creatTime = null;
                this.catNew.modifyTime = null;
                this.catNew.is_effective = false;
            },
            /*catModify实体初始化*/
            initCatModify(){
                this.catModify.id = null;
                this.catModify.name = null;
                this.catModify.creatTime = null;
                this.catModify.modifyTime = null;
                this.catModify.is_effective = false;
            },
            /*cat设置*/
            catSet(e){
                this.cat.id = e.id;
                this.cat.name = e.name;
                this.cat.creatTime = e.creatTime;
                this.cat.modifyTime = e.modifyTime;
                this.cat.is_effective = e.is_effective;
            },
            /*catNew设置*/
            catNewSet(e){
                this.catNew.id = e.id;
                this.catNew.name = e.name;
                this.catNew.creatTime = e.creatTime;
                this.catNew.modifyTime = e.modifyTime;
                this.catNew.is_effective = e.is_effective;
            },
            /*catModify设置*/
            catModifySet(e){
                this.catModify.id = e.id;
                this.catModify.name = e.name;
                this.catModify.creatTime = e.creatTime;
                this.catModify.modifyTime = e.modifyTime;
                this.catModify.is_effective = e.is_effective;
            },
            /*得到表数据*/
            getTable(e) {
                this.axios({
                  method: 'get',
                  url: '/api/sort/list',
                  params: {
                    'page':e.pageInfo.page,
                    'pageSize':e.pageInfo.pageSize,
                    'catName':e.catName
                  }
                }).then(function (response) {
                    this.data1=response.data.data;
                    this.total=response.data.totalCount;
                }.bind(this)).catch(function (error) {
                  alert(error);
                });
            },
            /*搜索按钮点击事件*/
            search(){
                this.initPageInfo();
                this.getTable({
                    "pageInfo":this.pageInfo,
                    "catName":this.catName
                });
            },
            /*分页点击事件*/
            pageSearch(e){
                this.pageInfo.page = e-1;
                this.getTable({
                    "pageInfo":this.pageInfo,
                    "catName":this.catName
                });
            },
            /*新建点击触发事件*/
            openNewModal(){
                this.newModal = true;
                this.initCatNew();
                this.data1.sort();
                this.count = 0;
                this.groupId = null;
            },
            /*新建modal的newOk点击事件*/
            newOk (catNew) {
                this.$refs[catNew].validate((valid) => {
                    if (valid) {
                        console.log(this.catNew);
                        if(this.catNew.name != null){
                            this.initCat();
                            this.catSet(this.catNew);
                            this.axios({
                                method: 'post',
                                url: 'api/sort/sort',
                                data: this.cat
                            }).then(function (response) {
                                //this.initcatNew();
                                this.getTable({
                                    "pageInfo":this.pageInfo,
                                    "catName":this.catName
                                });
                                this.$Message.info('新建成功');
                            }.bind(this)).catch(function (error) {
                                alert(error);
                            });
                            this.newModal = false;
                        }else{
                            this.$Message.error('请输入目录名称！');
                            this.loading = false;
                            this.$nextTick(() => {
                                this.loading = true;
                            });
                        }
                    }else {
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
            openModifyModal(){
                if(this.count > 1 || this.count < 1){
                    this.modifyModal= false;
                    this.$Message.warning('请至少选择一项(且只能选择一项)');
                }else{
                    this.modifyModal = true;
                }
            },
            openLookModifyModal(e){
                    this.catModifySet(e);
                    this.modifyModal = true;
            },
            /*修改modal的modifyOk点击事件*/
             modifyOk (catModify) {
                 console.log(this.cat);
                this.$refs[catModify].validate((valid) => {
                    if (valid) {
                        this.initCat();
                        this.catSet(this.catModify);
                        this.axios({
                          method: 'put',
                          url: 'api/sort/update/'+this.cat.id,
                          data: this.cat
                        }).then(function (response) {
                            //this.initcatNew();
                            this.getTable({
                                "pageInfo":this.pageInfo,
                                "catName":this.catName
                            });
                            this.$Message.info('修改成功');
                        }.bind(this)).catch(function (error) {
                          alert(error);
                        });
                        this.modifyModal = false;
                    }else {
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
            cancel () {
                this.$Message.info('点击了取消');
            },
            /*table选择后触发事件*/
            change(e){
                if(e.length==1){
                    this.catModifySet(e['0']);
                }
                this.setGroupId(e);
            },
            /*通过选中的行设置groupId的值*/
            setGroupId(e){
                this.groupId=[];
                this.count=e.length;
                for (var i = 0; i <= e.length - 1; i++) {
                    this.groupId.push(e[i].id);
                }
            },
            /*删除table中选中的数据*/
            del(){
                if(this.groupId!=null && this.groupId!=""){
                    this.axios({
                      method: 'delete',
                      url: 'api/sort/delete/',
                      data: this.groupId
                    }).then(function (response) {
                        this.getTable({
                            "pageInfo":this.pageInfo,
                            "catName":this.catName
                        });
                        this.groupId=null;
                        this.count=0;
                        this.$Message.info('删除成功');
                    }.bind(this)).catch(function (error) {
                        alert(error);
                    });
                }
            },
            /*表格中双击事件*/
            dblclick(e){
                this.catModifySet(e);
                this.modifyModal = true;
                this.data1.sort();
            },
            /*流程配置*/
            relationSet(e){
                this.roleModal = true;
                this.data2 = [];
                this.axios({
                  method: 'get',
                  url: '/relations/'+e.id
                }).then(function (response) {
                    var roleList = [];
                    for(var i in response.data){
                        roleList.push(response.data[i].roleId);
                    }
                    for(var i in this.data2Temp){
                        if(roleList.indexOf(this.data2Temp[i].id) == -1){
                            this.data2.push({
                                "id": this.data2Temp[i].id,
                                "name": this.data2Temp[i].name,
                                "describe": this.data2Temp[i].describe,
                                "userId": e.id,
                                "_checked": false
                            });
                        }else {
                            this.data2.push({
                                "id": this.data2Temp[i].id,
                                "name": this.data2Temp[i].name,
                                "describe": this.data2Temp[i].describe,
                                "userId": e.id,
                                "_checked": true
                            });
                        }
                    }
                }.bind(this)).catch(function (error) {
                  alert(error);
                });
            },
            /*配置角色modal确认按钮点击事件*/
            roleOk(){
                if(this.relationList!=null){
                    this.axios({
                      method: 'post',
                      url: '/relations',
                      data: this.relationList
                    }).then(function (response) {
                        this.$Message.info('配置成功');
                    }.bind(this)).catch(function (error) {
                      alert(error);
                    });
                    this.relationList = null;
                }
            },
            /*配置角色modal中表选择改变事件*/
            change2(e){
                this.relationList = [];
                if(e.length == 0){
                    this.relationList.push({
                        "userId": this.data2[0].userId
                    });
                }
                for (var i in e) {
                    this.relationList.push({
                        "userId": e[i].userId,
                        "roleId": e[i].id
                    });
                }
            },
            reversActive(isActive){
                if(isActive == true){
                    return "已激活";
                }else{
                    return "未激活";
                }
            }
        }
    }
</script>