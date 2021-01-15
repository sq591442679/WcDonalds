<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">修改入职记录</h1>
      <el-row>
        <el-select v-model="chosenRecord" placeholder="请选择要修改的员工" class="mySelect">
          <el-option
            v-for="item in recordList"
            :key="item"
            :value="item"
            :label="item"
          >
          </el-option>
        </el-select>
      </el-row>
        <el-date-picker v-model="exitTime" type="date" placeholder="选择离职日期" class="myDate" style="width: 500px"></el-date-picker>
      <el-row>
        <el-button type="primary" @click="modifyRecord(chosenRecord, exitTime)" class="sureButton">确定修改</el-button>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "ModifyRecord",
  data() {
    return {
      recordList: [],
      chosenRecord: '',
      exitTime: ''
    }
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
    modifyRecord(record, exitTime) {
      console.log(record);
      if (record === '' || exitTime === '') {
        this.$message.error('请填写完整信息');
      }
      else {
        axios
          .post(
            'http://localhost:8080/modifyRecord',
            {
              'record': record,
              'exitTime': exitTime
            }
          )
        alert('修改成功！');
        window.location = '/recordOperation/modifyRecord';
      }
    }
  },
  mounted() {
    this.init();
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