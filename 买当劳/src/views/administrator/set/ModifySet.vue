<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">修改套餐信息</h1>
      <el-row>
        <el-col :span="25">
          <el-select v-model="chosenSet" placeholder="请选择要修改的套餐" style="width: 350px; margin-top: 10px">
            <el-option
              v-for="item in sets"
              :key="item"
              :value="item"
              :label="item"
            >
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="25">
          <el-button type="primary" @click="queryFood" style="margin-top: 10px; margin-left: 10px">查询该套餐信息</el-button>
        </el-col>
      </el-row>
      <section>
        <div>
          <el-input v-model="cost" placeholder="请输入套餐售价" class="myInput"></el-input>
          <section v-for="(value, index) in setContent" :key="index">
              <el-input v-model="setContent[index]" :disabled="true" style="width: 200px; margin-top: 10px;"></el-input>
              <el-input v-model="setFoodNum[index]" placeholder="请输入食品数量" style="width: 200px; margin-top: 10px; margin-left: 10px;"></el-input>
              <el-button type="danger" icon="el-icon-delete" circle @click="removeFood(index)" style="margin-left: 10px"></el-button>
          </section>
        </div>
      </section>
      <section v-for="(value, index) in addFood" :key="index">
        <section v-if="index === 0">
          <el-row>
            <el-col :span="25">
              <!--              <el-input v-model="setContent[index]" placeholder="输入食品"></el-input>-->
              <el-select v-model="addFood[index]" placeholder="请选择食品" clearable class="mySelect">
                <el-option
                  v-for="item in notSelectedFoods"
                  :key="item"
                  :value="item"
                  :label="item"
                >
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="25">
              <el-input v-model="addFoodNum[index]" placeholder="请输入食物数量" style="width: 200px; margin-top: 10px;"></el-input>
            </el-col>
            <el-col :span="4">
              <el-button type="primary" icon="el-icon-plus"
                         style="margin-left: 10px; margin-top: 10px;" circle @click="addLastItems(index, '1')"></el-button>
            </el-col>
          </el-row>
        </section>
        <section v-if="index> 0 ">
          <el-row>
            <el-col :span="25">
              <!--              <el-input v-model="setContent[index]" placeholder="输入食品"/>-->
              <el-select v-model="addFood[index]" placeholder="请选择食品" class="mySelect">
                <el-option
                  v-for="item in notSelectedFoods"
                  :key="item"
                  :value="item"
                  :label="item"
                >
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="25">
              <el-input v-model="addFoodNum[index]" placeholder="请输入食物数量" style="width: 200px; margin-top: 10px;"></el-input>
            </el-col>
            <el-col :span="4">
              <el-button type="danger" icon="el-icon-delete"
                         style="margin-left:10px; margin-top: 10px;" circle @click="removeLastItems(index, '1')"/>
            </el-col>
          </el-row>
        </section>
      </section>
      <el-button type="primary" @click="modifySet" class="sureButton">确认修改</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ModifySet",
  data() {
    return {
      cost: '',
      chosenSet: '',
      setContent: [], //套餐中的菜品
      setFoodNum: [],
      notSelectedFoods: [], //没在套餐中的其他菜品
      deleteFood: [],
      addFood: [''],
      addFoodNum: [''],
      sets:[]
    }
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
    queryFood() {
      if (this.chosenSet === '') {
        alert('请选择套餐');
        return;
      }
      axios
        .post(
          'http://localhost:8080/superSet',
          {
            'id': this.chosenSet
          }
        )
        .then(
          (response) => {
            console.log(response)
            this.setContent = response.data.setFoodName;
            this.setFoodNum = response.data.setFoodNum;
            this.notSelectedFoods = response.data.otherFood;
          }
        )
    },
    addLastItems(index, type) {
      if (type === '1') {
        const lastItem = this.addFood[this.addFood.length - 1]
        if (lastItem.trim() === '' || this.addFoodNum[this.addFoodNum.length - 1] === '' || this.addFood[this.addFood.length - 1] === '') {
          this.$message.error('请您填写完一项后继续追加')
        }
        else {
          this.addFood.push('');
          let i = 0, len = this.notSelectedFoods.length;
          for (i = 0; i < len; i++) {
            if (this.notSelectedFoods[i] === lastItem) {
              this.notSelectedFoods.splice(index, 1);
            }
          }
        }
      }
    },
    removeLastItems(index, type) {
      if (type === '1') {
        this.notSelectedFoods.push(this.addFood[index])
        this.addFood.splice(index, 1);
        this.addFoodNum.splice(index, 1);
      }
    },
    removeFood(index) {
      this.deleteFood.push(this.setContent[index]);
      this.setContent.splice(index, 1);
      this.setFoodNum.splice(index, 1);
    },
    modifySet() {
      let i = 0, flag = false;
      for (i = 0; i < this.setFoodNum.length; ++i) {
        if (this.setFoodNum[i] === '') {
          flag = true;
        }
      }
      if (this.cost === '' || this.chosenSet === '' || flag === true) {
        this.$message.error('请填写完整信息');
      }
      else {
        console.log(this.chosenSet);
        axios
          .post(
            'http://localhost:8080/superModifySet',
            {
              'cost': [this.cost],
              'set': [this.chosenSet],
              'modifyFoodName': this.setContent,
              'modifyFoodNum': this.setFoodNum,
              'addFoodName': (this.addFood[0] === '' ? [] : this.addFood),
              'addFoodNum': (this.addFood[0] === '' ? [] : this.addFoodNum),
              'deleteFoodName': this.deleteFood
            }
          )
        alert('修改成功！');
        window.location = '/setOperation/modifySet';
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
  width: 200px;
  margin-right: 10px;
}

.sureButton {
  margin-top: 10px;
  position: relative;
  left: 50%;
  transform: translate(-50%);
}
</style>