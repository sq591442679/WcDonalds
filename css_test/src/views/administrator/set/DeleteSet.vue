<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">删除套餐</h1>
      <el-select v-model="chosenSet" placeholder="请选择要删除的套餐" class="mySelect">
        <el-option
          v-for="item in sets"
          :key="item"
          :value="item"
          :label="item"
        >
        </el-option>
      </el-select>
      <br>
      <el-button @click="deleteSet(chosenSet)" type="primary" class="sureButton">确认删除</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "DeleteSet",
  data() {
    return {
      chosenSet: '',
      sets: [],
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      axios
        .post(
          'http://localhost:8080/querySet',
          {
            'index': 1
          }
        )
        .then(
          (response) => {
            this.sets = response.data;
          }
        )
    },
    deleteSet(chosenSet) {
      if (chosenSet === '') {
        this.$message.error('请完整填写信息');
      }
      else {
        axios
          .post(
            'http://localhost:8080/deleteSet',
            {
              'deleteSet': chosenSet //todo
            }
          )
          .then(
            (response) => {
              if (response.data === 1) {
                alert('删除成功！');
                window.location = '/setOperation/deleteSet';
              }
              else {
                this.$message.error('该套餐编号不存在！');
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
  width: 400px;
}

.mySelect {
  margin-top: 10px;
  width: 400px;
  margin-right: 10px;
}

.sureButton {
  margin-top: 10px;
  position: relative;
  left: 50%;
  transform: translate(-50%);
}
</style>