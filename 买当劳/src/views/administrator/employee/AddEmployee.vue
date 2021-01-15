<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">添加员工信息</h1>
      <el-input v-model="name" class="myInput" placeholder="请输入员工姓名"></el-input>
      <br>
      <el-input v-model="age" class="myInput" placeholder="请输入员工年龄"></el-input>
      <br>
      <el-select v-model="position" placeholder="请选择员工职位" class="mySelect">
        <el-option
          v-for="item in positionList"
          :key="item"
          :label="item"
          :value="item"
        >
        </el-option>
      </el-select>
      <br>
      <el-input v-model="telephone" class="myInput" placeholder="请输入员工电话"></el-input>
      <br>
      <el-button type="primary" class="sureButton" @click="addEmployee(name, age, position, telephone)">添加</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "AddEmployee",
  data() {
    return {
      name: '',
      age: '',
      position: '',
      positionList: '',
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
          'http://localhost:8080/queryPosition',
          {
            'index': 1
          }
        )
        .then(
          (response) => {
            this.positionList = response.data;
          }
        )
    },
    addEmployee(name, age, position, telephone) {
      if (name === '' || age === '' || position === '' || telephone === '') {
        this.$message.error('请完整填写信息');
      }
      else {
        axios
          .post(
            'http://localhost:8080/addEmployee',
            {
              'name': name,
              'age': age,
              'position': position,
              'telephone': telephone
            }
          )
        .then(
          (response) => {
            if (response.data === 1) {
              alert('添加成功！');
              window.location = '/employeeOperation/addEmployee'
            }
            else if (response.data === 0) {
              alert('该员工已存在！');
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