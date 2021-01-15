<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">删除职位信息</h1>
      <el-row>
        <el-select v-model="chosenPositionName" placeholder="请选择要删除的职位" class="mySelect">
          <el-option
            v-for="item in positionNames"
            :key="item"
            :label="item"
            :value="item"
          >
          </el-option>
        </el-select>
      </el-row>
      <el-button type="primary" class="sureButton" @click="deleteEmployee">删除</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "DeletePosition",
  data() {
    return {
      positionNames: [],
      chosenPositionName: ''
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
    deleteEmployee() {
      console.log(this.chosenPositionName);
      if (this.chosenPositionName === '') {
        this.$message.error('请完整填写信息');
      }
      else {
        axios
          .post(
            'http://localhost:8080/deletePosition',
            {
              'positionName': this.chosenPositionName
            }
          )
          .then(
            (response) => {
              if (response.data === 1) {
                alert('删除成功！');
                window.location = '/positionOperation/deletePosition';
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