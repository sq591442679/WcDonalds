<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">删除入职记录</h1>
      <el-row>
        <el-select v-model="chosenRecord" placeholder="请选择要删除的员工" class="mySelect">
          <el-option
            v-for="item in recordList"
            :key="item"
            :value="item"
            :label="item"
          >
          </el-option>
        </el-select>
      </el-row>
      <el-row>
        <el-button type="primary" @click="deleteRecord(chosenRecord)" class="sureButton">确定删除</el-button>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "DeleteRecord",
  data() {
    return {
      recordList: [],
      chosenRecord: '',
      exitTime: ''
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      axios
        .post(
          'http://localhost:8080/queryRecord',
          {
            'index': 1
          }
        )
        .then(
          (response) => {
            this.recordList = response.data;
          }
        )
    },
    deleteRecord(record) {
      if (record === '') {
        this.$message.error('请填写完整信息');
      }
      else {
        axios
          .post(
            'http://localhost:8080/deleteRecord',
            {
              'record': record
            }
          )
          .then(
            (response) => {
              alert('删除成功！');
              window.location = '/recordOperation/deleteRecord';
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

.myDate {
  margin-top: 10px;
  position: relative;
  width: 500px;
  left: 50%;
  transform: translate(-50%);
}

.sureButton {
  margin-top: 10px;
  position: relative;
  left: 50%;
  transform: translate(-50%);
}
</style>