<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">添加入职记录</h1>
      <el-select v-model="chosenEmployeeName" placeholder="请选择要添加的员工" class="mySelect">
        <el-option
          v-for="item in employeeNameList"
          :key="item"
          :value="item"
          :label="item"
        >
        </el-option>
      </el-select>
      <br>
      <el-select v-model="chosenRestAddress" placeholder="请选择员工所在餐厅" class="mySelect">
        <el-option
          v-for="item in restAddressList"
          :key="item"
          :value="item"
          :label="item"
        >
        </el-option>
      </el-select>
      <br>
      <el-select v-model="chosenPosition" placeholder="请选择员工职位" class="mySelect">
        <el-option
          v-for="item in positionList"
          :key="item"
          :value="item"
          :label="item"
        >
        </el-option>
      </el-select>
      <br>
      <el-row>
        <el-date-picker v-model="entryTime" type="date" placeholder="选择入职日期" class="myDate" style="width: 500px"></el-date-picker>
      </el-row>
      <el-row>
        <el-button type="primary" @click="addRecord(chosenEmployeeName, chosenRestAddress, entryTime)" class="sureButton">确定添加</el-button>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "AddRecord",
  data() {
    return {
      chosenEmployeeName: '',
      employeeNameList: [],
      chosenRestAddress: '',
      restAddressList: [],
      positionList: [],
      chosenPosition: '',
      entryTime: '',
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      axios
        .post(
          'http://localhost:8080/superRecord',
          {

          }
        )
        .then(
          (response) => {
            this.employeeNameList = response.data.employeeList;
            this.restAddressList = response.data.restaurantList;
            this.positionList = response.data.positionList;
          }
        )
    },
    addRecord(employeeName, restAddress, entryTime) {
      //console.log(entryTime)
      if (employeeName === '' || restAddress === '' || entryTime === '') {
        this.$message.error('请填写完整信息');
      }
      else {
        axios
          .post(
            'http://localhost:8080/addRecord',
            {
              'employeeIdName': employeeName,
              'restAddress': restAddress,
              'entryTime': entryTime,
              'position': this.chosenPosition
            }
          )
          .then(
            (response) => {
              alert('添加成功！');
              window.location = '/recordOperation/addRecord';
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