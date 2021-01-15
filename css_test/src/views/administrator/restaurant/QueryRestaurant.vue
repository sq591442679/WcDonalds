<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">查询餐厅</h1>
      <el-row style="max-height: 400px; overflow-y: auto">
        <ul>
          <li v-for="item in restaurants" style="margin-bottom: 10px">
            {{ item }}
          </li>
        </ul>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "QueryRestaurant",
  data() {
    return {
      restaurants: [],
    }
  },
  mounted() {
    this.queryRestaurant();
  },
  methods: {
    queryRestaurant() {
      axios
        .post(
          'http://localhost:8080/queryRestaurant',
          {
            'index': 1
          }
        )
        .then(
          (response) => {
            if (response.data === 0) {
              this.$message.error('餐厅为空！');
            }
            else {
              this.restaurants = response.data;
            }
          }
        )
    }
  }
}
</script>

<style scoped>

</style>