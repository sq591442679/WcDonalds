<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">添加职位信息</h1>
      <el-input v-model="positionName" class="myInput" placeholder="请输入职位名称"></el-input>
      <br>
      <el-input v-model="salary" class="myInput" placeholder="请输入职位薪资"></el-input>
      <br>
      <el-button type="primary" class="sureButton" @click="addPosition">添加</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "AddPosition",
  data() {
    return {
      positionName: '',
      salary: '',
    }
  },
  methods: {
    addPosition() {
      if (this.positionName === '' || this.salary === '') {
        this.$message.error('请填写完整信息');
      }
      else {
        axios
          .post(
            'http://localhost:8080/addPosition',
            {
              'positionName': this.positionName,
              'salary': this.salary
            }
          )
          .then(
            (response) => {
              if (response.data === 0) {
                this.$message.error('职位已存在!');
              }
              else {
                alert('添加成功');
                window.location = '/positionOperation/addPosition';
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