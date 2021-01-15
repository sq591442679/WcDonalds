<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">添加套餐</h1>
      <el-input v-model="name" placeholder="请输入套餐名称" class="myInput"></el-input>
      <br>
      <el-input v-model="cost" placeholder="请输入套餐售价" class="myInput"></el-input>
      <section v-for="(value, index) in setContent" :key="index">
        <section v-if="index === 0">
          <el-row style="margin-top:10px;">
            <el-col :span="25">
<!--              <el-input v-model="setContent[index]" placeholder="输入食品"></el-input>-->
              <el-select v-model="setContent[index]" placeholder="请选择食品" class="mySelect">
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
              <el-input v-model="foodNum[index]" placeholder="请输入食物数量" style="width: 200px"></el-input>
            </el-col>
            <el-col :span="4">
              <el-button type="primary" icon="el-icon-plus"
                        style="margin-left: 40px;" circle @click="addLastItems(index, '1')"></el-button>
            </el-col>
          </el-row>
        </section>
        <section v-if="index> 0 ">
          <el-row style="margin-top:10px;">
            <el-col :span="25">
<!--              <el-input v-model="setContent[index]" placeholder="输入食品"/>-->
              <el-select v-model="setContent[index]" placeholder="请选择食品" class="mySelect">
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
              <el-input v-model="foodNum[index]" placeholder="请输入食物数量" style="width: 200px"></el-input>
            </el-col>
            <el-col :span="4">
              <el-button type="danger" icon="el-icon-delete"
                         style="margin-left:40px;" circle @click="removeLastItems(index, '1')"/>
            </el-col>
          </el-row>
        </section>
      </section>
      <br>
      <el-button type="primary" @click="addSet(name, cost, setContent)" class="sureButton">确定添加</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "AddSet",
  data() {
    return {
      name: '',
      cost: '',
      setContent: [''], //套餐里的菜品
      foodNum: [''],
      allFoods: ['1:薯条', '2:杨老板的骨灰', '3:杨老板的妈妈的骨灰'], //从后端取得的所有食品的列表
      notSelectedFoods: ['1:薯条', '2:杨老板的骨灰', '3:杨老板的妈妈的骨灰']
    }
  },
  mounted: function () {
    //this.test();
    this.getFoods();
  }
  ,
  methods: {
    addLastItems(index, type) {
      if (type === '1') {
        const lastItem = this.setContent[this.setContent.length - 1]
        if (lastItem.trim() === '' || this.foodNum[this.foodNum.length - 1] === '') {
          this.$message.error('请您填写完一项后继续追加')
        }
        else {
          this.setContent.push('');
          this.foodNum.push('');
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
        this.notSelectedFoods.push(this.setContent[index])
        this.setContent.splice(index, 1);
        this.foodNum.splice(index, 1);
      }
    },
    test() {
      alert('NMSL');
    },
    getFoods() {
      axios
        .post(
          'http://localhost:8080/queryFood',
          {
            'index': 1
          }
        )
      .then(
        (response) => {
          this.allFoods = response.data;
          this.notSelectedFoods = this.allFoods;
          //console.log(this.allFoods);
        }
      )
    },
    addSet(name, cost, setContent) {
      //console.log(setContent)
      if (name === '' || cost === '' || setContent[setContent.length - 1] === '' || this.foodNum[this.foodNum.length - 1] === '') {
        this.$message.error('请填写完整信息');
      }
      else {
        //console.log(setContent);
        //console.log(this.setFoodNum);
        axios
          .post(
            'http://localhost:8080/superAddSet',
            {
              'name': [name],
              'cost': [cost],
              'foodName': this.setContent,
              'foodNum': this.foodNum
            }
          )
          .then(
            (response) => {
              if (response.data === 0) {
                alert('套餐已存在！');
              }
              else if (response.data === 1) {
                alert('添加成功！');
                window.location = '/setOperation/addSet';
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

.mySelect {
  /*margin-top: 10px;*/
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