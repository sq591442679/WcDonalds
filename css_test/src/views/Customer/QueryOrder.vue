<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">查询订单</h1>
      <el-row style="max-height: 600px; overflow-y: auto">
        <ul>
          <li v-for="item in order" style="margin-bottom: 10px">
            {{ item }}
          </li>
        </ul>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "QueryOrder",
  data() {
    return {
      order: [],
    }
  },
  mounted() {
    this.queryOrder();
  },
  methods: {
    queryOrder() {
      axios
        .post(
          'http://localhost:8080/queryOrderTime',
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
              this.order = response.data;
            }
          }
        )
    }
  }
}
</script>

<style scoped>
.sureButton {
  position: relative;
  left: 50%;
  transform: translate(-50%);
}
</style>