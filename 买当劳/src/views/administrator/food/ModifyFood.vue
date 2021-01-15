<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">修改食品</h1>
      <el-row>
        <el-select v-model="foodName" placeholder="请选择要修改的食品" class="mySelect">
          <el-option
            v-for="item in foods"
            :key="item"
            :label="item"
            :value="item"
          >
          </el-option>
        </el-select>
      </el-row>
      <el-input v-model="cost" placeholder="请输入食品售价" class="myInput"></el-input>
      <br>
      <el-button type="primary" class="sureButton" @click="modifyFood(foodName, cost)">修改</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ModifyFood",
  data() {
    return {
      foods:[],
      foodName: '',
      cost: ''
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      axios
        .post(
          'http://localhost:8080/queryFood',
          {
            'index': 1
          }
        )
        .then(
          (response) => {
            this.foods = response.data;
          }
        )
    },
    modifyFood(foodName, cost) {
      if (foodName === '' || cost === '') {
        this.$message.error('请完整填写信息');
      }
      else {
        axios
          .post(
            'http://localhost:8080/modifyFood',
            {
              'foodName': foodName,
              'foodCost': cost.toString()
            }
          )
          .then(
            (response) => {
              if (response.data === 1) {
                alert('修改成功！');
                window.location = '/foodOperation/modifyFood';
              }
              else {
                alert('编号冲突！');
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

.mySelect {
  margin-top: 10px;
  width: 500px;
}
</style>