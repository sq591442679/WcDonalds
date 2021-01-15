<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">修改员工信息</h1>
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
      <el-row>
        <el-select v-model="chosenPosition" placeholder="请选择要修改的职位" class="mySelect">
          <el-option
            v-for="item in positionList"
            :key="item"
            :label="item"
            :value="item"
          >
          </el-option>
        </el-select>
      </el-row>
      <el-input v-model="age" class="myInput" placeholder="请输入员工年龄"></el-input>
      <br>
      <el-input v-model="telephone" class="myInput" placeholder="请输入员工电话"></el-input>
      <br>
      <el-button type="primary" class="sureButton" @click="modifyEmployee(chosenIdName, age, telephone)">修改</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ModifyEmployee",
  data() {
    return {
      idNames: [],
      positionList: [],
      chosenIdName: '',
      chosenPosition: '',
      age: '',
      telephone: ''
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      axios
        .post(
          'http://localhost:8080/superEmployee',
          {
          }
        )
        .then(
          (response) => {
            this.idNames = response.data.name;
            this.positionList = response.data.job;
          }
        )
    },
    modifyEmployee(idName, age, telephone) {
      if (idName === '' || age === '' || telephone === '') {
        this.$message.error('请完整填写信息');
      } else {
        axios
          .post(
            'http://localhost:8080/modifyEmployee',
            {
              'idName': idName,
              'age': age,
              'telephone': telephone,
              'position': this.chosenPosition
            }
          )
          .then(
            (response) => {
              if (response.data === 1) {
                alert('修改成功！');
                window.location = '/employeeOperation/modifyEmployee'
              } else if (response.data === 0) {
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