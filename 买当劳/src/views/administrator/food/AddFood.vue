<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">添加食品</h1>
      <el-input v-model="name" placeholder="请输入食品名称" class="myInput"></el-input>
      <br>
      <el-input v-model="cost" placeholder="请输入食品售价" class="myInput"></el-input>
      <br>
      <el-button type="primary" class="sureButton" @click="addFood(name, cost)">添加</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "AddFood",
  data() {
    return {
      name: '',
      cost: ''
    }
  },
  methods: {
    addFood(name, cost) {
      if (name === '' || cost === '') {
        this.$message.error('请完整填写信息');
      }
      else {
        axios
          .post(
            'http://localhost:8080/addFood',
            {
              'name': name,
              'cost': cost
            }
          )
        .then(
          (response) => {
            if (response.data === 1) {
              alert('添加成功！');
              window.location = '/foodOperation/addFood';
            }
            else {
              this.$message.error('编号冲突！');
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