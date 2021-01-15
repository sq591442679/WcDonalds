<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">删除餐厅</h1>
      <el-row>
        <el-select v-model="location" placeholder="请选择要删除的餐厅" class="mySelect">
          <el-option
            v-for="item in restaurants"
            :key="item"
            :label="item"
            :value="item"
          >
          </el-option>
        </el-select>
      </el-row>
      <el-button type="primary" class="sureButton"  @click="deleteRestaurant">确认删除</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "DeleteRestaurant",
  data() {
    return {
      location: '',
      restaurants: [] //todo
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      axios
        .post(
          'http://localhost:8080/queryRestaurant',
          {
            'index': 1
          }
        )
        .then(
          (response) => {
            this.restaurants = response.data;
          }
        )
    },
    deleteRestaurant() {
      if (this.location === '') {
        this.$message.error('请完整填写信息');
      }
      else {
        axios
          .post(
            'http://localhost:8080/deleteRestaurant',
            {
              'location': this.location
            }
          )
          .then(
            (response) => {
              if (response.data === 0) {
                this.$message.error('餐厅不存在！')
              }
              else if (response.data === 1) {
                alert('删除成功');
                window.location = '/restaurantOperation/deleteRestaurant';
              }
            }
          )
      }
    }
  }
}
</script>

<style scoped>
.myInput {
  width: 500px;
}

.sureButton {
  margin-top: 10px;
  position: relative;
  left: 50%;
  transform: translate(-50%);
}

.mySelect {
  width: 500px;
}
</style>