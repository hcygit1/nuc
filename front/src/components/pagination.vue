
<template>
  <div class="box-footer clearfix" id="page">
    <div class="pagination-status">
      总计<span>{{dataList.totalPages}}</span>页，
      每页<select v-model="limit" @change="btnClick(page, limit)">
        <option v-for="item in options" :value="item.value">
          {{ item.text }}
        </option>
      </select>条，
      共<span>{{dataList.totalCount}}</span>条数据
    </div>
    <ul class="pagination pagination-sm no-margin pull-right">
      <li :class="{disabled:!dataList.hasPrePage}"  @click="btnClick(1)"><a>&laquo;</a></li>
      <li v-for="pIndex in dataList.slider" :class="{active: pIndex==dataList.page}" @click="btnClick(pIndex,limit)">
        <a>{{pIndex}}</a>
      </li>
      <li :class="{disabled:!dataList.hasNextPage}" @click="btnClick(dataList.totalPages,limit)"><a>&raquo;</a></li>
    </ul>
  </div>
</template>

<script>
  export default {
    template: '#page',
    props: [
      'dataList',
      'customOptions'
    ],
    data: function(){
      return {
        page: 0,
        limit: 1,
        options: [
          { text: 5, value: 5 },
          { text: 10, value: 10 },
          { text: 20, value: 20 },
          { text: 50, value: 50 },
          { text: 100, value: 100 },
          { text: 500, value: 500 },
          { text: 1000, value: 1000 },
        ]
      }
    },
    created(){
      this.page = this.dataList.page;
      this.limit = this.dataList.limit;
      if (this.customOptions) {
        this.options = this.customOptions;
      }
//      console.log("分页测试：created"+this.page +","+this.limit);
    },
    beforeUpdate(){
      this.page = this.dataList.page;
      this.limit = this.dataList.limit;
//      console.log("分页测试：beforeUpdate"+this.page +","+this.limit);
    },
    methods: {
      btnClick: function(page, limit){
        this.page = page;
        this.limit = limit;
        this.$emit('change-page', page, limit)
      }
    }
  }
</script>
