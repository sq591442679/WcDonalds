<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">查询入职记录</h1>
      <el-row style="max-height: 400px; overflow-y: auto">
        <ul>
          <li v-for="item in records" style="margin-bottom: 10px">
            {{ item }}
          </li>
        </ul>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "QueryRecord",
  data() {
    return {
      records: [],
    }
  },
  mounted() {
    this.queryRecord();
  },
  methods: {
    queryRecord() {
      axios
        .post(
          'http://localhost:8080/queryRecord',
          {
            'index': 1
          }
        )
        .then(
          (response) => {
            if (response.data === 0) {
              this.$message.error('订单为空！');
            }
            else {
              this.records = response.data;
            }
          }
        )
    }
  }
}
</script>

<style scoped>

</style>