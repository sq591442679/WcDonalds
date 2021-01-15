<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">查询餐厅</h1>
      <el-row v-if="flag">
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
      restaurants: [
        {
          location: '大运村',
          volume: '120',
          telephone: '7652'
        },
        {
          location: '花园路',
          volume: '90',
          telephone: '78520'
        }
      ],
      flag: false
    }
  },
  mounted() {
    this.queryRestaurants();
  },
  methods: {
    queryRestaurants() {
      this.flag = true;
      axios
        .post(
          'http://localhost:8080/queryRestaurant',
          {
            'index': 1
          }
        )
        .then(
          (response) => {
            this.flag = true;
            if (response.data === 0) {
              this.$message.error('餐厅列表为空');
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
.sureButton {
  position: relative;
  left: 50%;
  transform: translate(-50%);
}
</style>