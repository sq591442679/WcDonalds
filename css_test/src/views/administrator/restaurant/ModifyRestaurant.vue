<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">修改餐厅信息</h1>
      <el-row>
        <el-select v-model="chosenLocation" placeholder="请选择要修改的餐厅" class="mySelect">
          <el-option
            v-for="item in restaurantLocations"
            :key="item"
            :label="item"
            :value="item"
          >
          </el-option>
        </el-select>
      </el-row>
      <el-input v-model="volume" placeholder="请输入餐厅容纳人数" class="myInput"></el-input>
      <br>
      <el-input v-model="telephone" placeholder="请输入餐厅电话" class="myInput"></el-input>
      <br>
      <el-button type="primary" class="sureButton" @click="modifyRestaurant(chosenLocation, volume, telephone)">确定修改</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ModifyRestaurant",
  data() {
    return {
      restaurantLocations: [],
      volume:'',
      telephone: '',
      chosenLocation: ''
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
            this.restaurantLocations = response.data;
          }
        )
    },
    modifyRestaurant(location, volume, telephone) {
      console.log(location + volume + telephone);
      if (location === '' || volume === '' || telephone === '') {
        this.$message.error('请完整填写信息');
      }
      else {
        axios
          .post(
            'http://localhost:8080/modifyRestaurant',
            {
              'location': location,
              'volume': volume.toString(),
              'telephone': telephone.toString()
            }
          )
          .then(
            (response) => {
              if (response.data === 1) {
                alert('修改成功！');
                window.location = '/restaurantOperation/modifyRestaurant';
              }
              else if (response.data === 0) {
                this.$message.error('请填写完整信息');
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

.mySelect {
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