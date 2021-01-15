<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">删除食品</h1>
      <el-row>
        <el-select v-model="chosenFood" placeholder="请选择要修改的食品" class="mySelect">
          <el-option
            v-for="item in foods"
            :key="item"
            :label="item"
            :value="item"
          >
          </el-option>
        </el-select>
      </el-row>
      <el-button type="primary" class="sureButton"  @click="deleteFood(chosenFood)">确认删除</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "DeleteFood",
  data() {
    return {
      foods: [],
      chosenFood: ''
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
            if (response.data === 0) {
              this.$message.error('订单为空！');
            }
            else {
              this.foods = response.data;
            }
          }
        )
    },
    deleteFood(chosenFood) {
      if (chosenFood === '') {
        this.$message.error('请完整填写信息');
      }
      else {
        axios
          .post(
            'http://localhost:8080/deleteFood',
            {
              'foodName': chosenFood
            }
          )
          .then(
            (response) => {
              if (response.data === 0) {
                alert('食品不存在！')
              }
              else if (response.data === 1) {
                alert('删除成功!');
                window.location = '/foodOperation/deleteFood';
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