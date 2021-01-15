<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">删除员工信息</h1>
      <el-row>
        <el-select v-model="chosenIdName" placeholder="请选择要修改的员工" class="mySelect">
          <el-option
            v-for="item in idNames"
            :key="item"
            :label="item"
            :value="item"
          >
          </el-option>
        </el-select>
      </el-row>
      <el-button type="primary" class="sureButton" @click="deleteEmployee(chosenIdName)">删除</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "DeleteEmployee",
  data() {
    return {
      idNames: [],
      chosenIdName: ''
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      axios
        .post(
          'http://localhost:8080/queryEmployee',
          {
            'index': 1
          }
        )
        .then(
          (response) => {
            this.idNames = response.data;
          }
        )
    },
    deleteEmployee(idName) {
      console.log(idName);
      if (idName === '') {
        this.$message.error('请完整填写信息');
      }
      else {
        axios
          .post(
            'http://localhost:8080/deleteEmployee',
            {
              'idName': idName
            }
          )
        .then(
          (response) => {
            if (response.data === 1) {
              alert('删除成功！');
              window.location = '/employeeOperation/deleteEmployee';
            }
            else if (response.data === 0) {
              alert('员工编号不存在！');
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