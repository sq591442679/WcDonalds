<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">修改职位信息</h1>
      <el-row>
        <el-select v-model="chosenPositionName" placeholder="请选择要修改的职位" class="mySelect">
          <el-option
            v-for="item in positionNames"
            :key="item"
            :label="item"
            :value="item"
          >
          </el-option>
        </el-select>
      </el-row>
      <el-input v-model="salary" class="myInput" placeholder="请输入职位薪资"></el-input>
      <br>
      <el-button type="primary" class="sureButton" @click="modifyPosition">修改</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ModifyPosition",
  data() {
    return {
      positionNames: [],
      chosenPositionName: '',
      salary: ''
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
            this.positionNames = response.data;
          }
        )
    },
    modifyPosition() {
      if (this.chosenPositionName === '' || this.salary === '') {
        this.$message.error('请完整填写信息');
      } else {
        axios
          .post(
            'http://localhost:8080/modifyPosition',
            {
              'positionName': this.chosenPositionName,
              'salary': this.salary,
            }
          )
          .then(
            (response) => {
              if (response.data === 1) {
                alert('修改成功！');
                window.location = '/positionOperation/modifyPosition'
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