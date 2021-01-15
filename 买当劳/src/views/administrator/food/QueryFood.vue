<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">查询食品</h1>
      <el-row style="max-height: 400px; overflow-y: auto">
        <ul>
          <li v-for="item in orders" style="margin-bottom: 10px">
            {{ item }}
          </li>
        </ul>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "QueryFood",
  data() {
    return {
      orders: [],
    }
  },
  mounted() {
    this.queryFood();
  },
  methods: {
    queryFood() {
      axios
        .post(
          'http://localhost:8080/queryFood',
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
              this.orders = response.data;
            }
          }
        )
    }
  }
}
</script>

<style scoped>

</style>