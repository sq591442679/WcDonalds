<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">添加餐厅</h1>
      <el-input v-model="location" placeholder="请输入餐厅地址" class="myInput"></el-input>
      <br>
      <el-input v-model="volume" placeholder="请输入餐厅容纳人数" class="myInput"></el-input>
      <br>
      <el-input v-model="telephone" placeholder="请输入餐厅电话" class="myInput"></el-input>
      <br>
      <el-button type="primary" class="sureButton" @click="newRestaurant(location, volume, telephone)">确定添加</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "NewRestaurant",
  data() {
    return {
      location: '',
      volume:'',
      telephone: ''
    }
  },
  methods: {
    newRestaurant(location, volume, telephone) {
      if (location === '' || volume === '' || telephone === '') {
        alert('请完整填写信息');
      }
      else {
        axios
          .post(
            'http://localhost:8080/newRestaurant',
            {
              'location': location,
              'volume': volume,
              'telephone': telephone
            }
          )
        .then(
          (response) => {
            if (response.data === 1) {
              alert('添加成功！');
              window.location ='/restaurantOperation/newRestaurant';
            }
            else if (response.data === 0) {
              this.$message.error('餐厅编号重复！');
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
  margin-top: 10px;
  width: 500px;
}

.sureButton {
  margin-top: 10px;
  position: relative;
  left: 50%;
  transform: translate(-50%);
}
</style>