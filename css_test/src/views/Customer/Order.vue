<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">开始点餐！</h1>
      <el-row>
        <el-select v-model="chosenRestaurant" placeholder="选择餐厅" class="mySelect">
          <el-option
            v-for="item in restaurantList"
            :value="item"
            :key="item"
            :label="item"
          >
          </el-option>
        </el-select>
      </el-row>
      <el-row>
        <el-select v-model="chosenAddress" placeholder="选择送餐地址" class="mySelect">
          <el-option
            v-for="item in addressList"
            :value="item"
            :key="item"
            :label="item"
          >
          </el-option>
        </el-select>
      </el-row>

      <section v-for="(value, index) in orderFoodName" :key="index">
        <section v-if="index === 0">
          <el-row style="margin-top:10px;">
            <el-col :span="25">
              <!--              <el-input v-model="setContent[index]" placeholder="输入食品"></el-input>-->
              <el-select v-model="orderFoodName[index]" placeholder="请选择食品" style="width: 200px">
                <el-option
                  v-for="item in foodList"
                  :key="item"
                  :value="item"
                  :label="item"
                >
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="25">
              <el-input v-model="orderFoodNum[index]" placeholder="请输入食物数量" style="width: 200px; margin-left: 10px"></el-input>
            </el-col>
            <el-col :span="4">
              <el-button type="primary" icon="el-icon-plus"
                         style="margin-left: 40px;" circle @click="addFood(index)"></el-button>
            </el-col>
          </el-row>
        </section>
        <section v-if="index> 0 ">
          <el-row style="margin-top:10px;">
            <el-col :span="25">
              <!--              <el-input v-model="setContent[index]" placeholder="输入食品"/>-->
              <el-select v-model="orderFoodName[index]" placeholder="请选择食品" style="width: 200px">
                <el-option
                  v-for="item in foodList"
                  :key="item"
                  :value="item"
                  :label="item"
                >
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="25">
              <el-input v-model="orderFoodNum[index]" placeholder="请输入食物数量" style="width: 200px; margin-left: 10px"></el-input>
            </el-col>
            <el-col :span="4">
              <el-button type="danger" icon="el-icon-delete"
                         style="margin-left:40px;" circle @click="removeFood(index)"/>
            </el-col>
          </el-row>
        </section>
      </section>

      <section v-for="(value, index) in orderSetName" :key="index">
        <section v-if="index === 0">
          <el-row style="margin-top:10px;">
            <el-col :span="25">
              <!--              <el-input v-model="setContent[index]" placeholder="输入食品"></el-input>-->
              <el-select v-model="orderSetName[index]" placeholder="请选择套餐" style="width: 200px">
                <el-option
                  v-for="item in setList"
                  :key="item"
                  :value="item"
                  :label="item"
                >
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="25">
              <el-input v-model="orderSetNum[index]" placeholder="请输入套餐数量" style="width: 200px; margin-left: 10px"></el-input>
            </el-col>
            <el-col :span="4">
              <el-button type="primary" icon="el-icon-plus"
                         style="margin-left: 40px;" circle @click="addSet(index)"></el-button>
            </el-col>
          </el-row>
        </section>
        <section v-if="index> 0 ">
          <el-row style="margin-top:10px;">
            <el-col :span="25">
              <!--              <el-input v-model="setContent[index]" placeholder="输入食品"/>-->
              <el-select v-model="orderSetName[index]" placeholder="请选择套餐" style="width: 200px">
                <el-option
                  v-for="item in setList"
                  :key="item"
                  :value="item"
                  :label="item"
                >
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="25">
              <el-input v-model="orderSetNum[index]" placeholder="请输入套餐数量" style="width: 200px; margin-left: 10px"></el-input>
            </el-col>
            <el-col :span="4">
              <el-button type="danger" icon="el-icon-delete"
                         style="margin-left:40px;" circle @click="removeSet(index)"/>
            </el-col>
          </el-row>
        </section>
      </section>

      <el-row>
        <el-button type="primary" @click="order" class="sureButton">确认点餐</el-button>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "Order",
  data() {
    return {
      imagebox: [
        {id:0,idView:require('../../images/roll/01.png')},
        {id:1,idView:require('../../images/roll/02.png')},
        {id:2,idView:require('../../images/roll/03.png')},
        {id:3,idView:require('../../images/roll/04.png')},
      ],
      setList: [], //所有的套餐
      foodList: [],
      restaurantList: [],
      addressList: [], //用户的所有订餐地址

      orderFoodName: [''], //点了的所有食物 id:name
      orderFoodNum: [''], //点了的食物数量
      orderSetName: [''],
      orderSetNum: [''],

      chosenRestaurant: '', //选中的餐厅
      chosenAddress: '', //选中的送餐地址

      totalCost: '', //弹框
      screenWidth: 0,

      id: '',
    }
  },
  mounted() {
    this.init();
    this.screenWidth =  window.innerWidth;
    this.setSize();
    // 窗口大小发生改变时,调用一次
    window.onresize = () =>{
      this.screenWidth =  window.innerWidth;
      this.setSize();
    }
  },
  methods: {
    init() {
      // axios
      //   .post(
      //     'http://localhost:8080/querySet',
      //     {
      //       'index': 1
      //     }
      //   )
      //   .then(
      //     (response) => {
      //       this.setList = response.data;
      //     }
      //   )
      axios
        .post(
          'http://localhost:8080/superQueryFood',
          {
            'index': 1
          }
        )
        .then(
          (response) => {
            this.foodList = response.data.food;
            this.setList = response.data.set;
            this.restaurantList = response.data.restaurant;
            this.addressList = response.data.address;
            this.id = response.data.id;
          }
        )
      // axios
      //   .post(
      //     'http://localhost:8080/queryRestaurant',
      //     {
      //       'index': 1
      //     }
      //   )
      //   .then(
      //     (response) => {
      //       this.restaurantList = response.data;
      //     }
      //   )
      // axios
      //   .post(
      //     'http://localhost:8080/queryAddress',
      //     {
      //       'index': 1
      //     }
      //   )
      //   .then(
      //     (response) => {
      //       this.addressList = response.data;
      //     }
      //   )
    },
    setSize:function () {
      // 通过浏览器宽度(图片宽度)计算高度
      this.bannerHeight = 400 / 1920 * this.screenWidth;
    },
    order() { //顾客下单
      let tmp = [new Date().getTime().toString()];
      console.log(this.orderFoodName);
      console.log(this.orderFoodNum);
      console.log(this.orderSetName);
      console.log(this.orderSetNum);
      console.log([this.chosenAddress]);
      console.log(tmp);

      // if ((this.orderSetName[this.orderSetName.length - 1] !== '' && this.orderSetNum[this.orderSetNum.length - 1] === '')
      //   || (this.orderSetName[this.orderSetName.length - 1] === '')) {
      //   this.$message.error('请填写完整信息');
      //   return;
      // }
      // if ((this.orderFoodName[this.orderFoodName.length - 1] !== '' && this.orderFoodNum[this.orderFoodNum.length - 1] === '')
      //   || (this.orderFoodName[this.orderFoodName.length - 1] === '')) {
      //   this.$message.error('请填写完整信息');
      //   return;
      // }
      if (((this.orderSetName[this.orderSetName.length - 1] === '' || this.orderSetNum[this.orderSetNum.length - 1] === '')
        && (this.orderFoodName[this.orderFoodName.length - 1] === '' || this.orderFoodNum[this.orderFoodNum.length - 1] === ''))
        || this.chosenAddress === '' || this.chosenRestaurant === '') {
        this.$message.error('请填写完整信息');
        return;
      }
      if (this.orderSetName[0] === '') {
        this.orderSetName = [];
        this.orderSetNum = [];
      }
      if (this.orderFoodName[0] === '') {
        this.orderFoodName = [];
        this.orderSetNum = [];
      }
      axios
        .post(
          'http://localhost:8080/superOrderFood',
          {
            'foodName': this.orderFoodName,
            'foodNum': this.orderFoodNum,
            'setName': this.orderSetName,
            'setNum': this.orderSetNum,
            'restAddress': [this.chosenRestaurant],
            'address': [this.chosenAddress],
            'orderTime': tmp
          }
        )
        .then(
          (response) => {
            console.log(response.data);
            this.totalCost = response.data;
            this.$alert(
              '本次订餐价格为'+this.totalCost.toString()+'元',
              '收银台',
              {
                confirmButtonText: '确定'
              }
            )
          }
        )
    },

    addFood(index) {
      const lastItem = this.orderFoodName[this.orderFoodName.length - 1]
      if (lastItem.trim() === '' || this.orderFoodNum[this.orderFoodNum.length - 1] === '') {
        this.$message.error('请您填写完一项后继续追加')
      }
      else if (this.foodList.length === 0 || this.foodList[0] === '') {
        this.$message.error('已经选择了所有食品')
      }
      else {
        this.orderFoodName.push('');
        this.orderFoodNum.push('');
        let i = 0, len = this.foodList.length;
        for (i = 0; i < len; i++) {
          if (this.foodList[i] === lastItem) {
            this.foodList.splice(index, 1);
          }
        }
      }
    },
    removeFood(index) {
      this.foodList.push(this.orderFoodName[index]);
      this.orderFoodName.splice(index, 1);
      this.orderFoodNum.splice(index, 1);
    },

    addSet(index) {
      const lastItem = this.orderSetName[this.orderSetName.length - 1]
      if (lastItem.trim() === '' || this.orderSetNum[this.orderSetNum.length - 1] === '') {
        this.$message.error('请您填写完一项后继续追加')
      }
      else if (this.setList.length === 0) {
        this.$message.error('已经选择了所有套餐')
      }
      else {
        this.orderSetName.push('');
        this.orderSetNum.push('');
        let i = 0, len = this.setList.length;
        for (i = 0; i < len; i++) {
          if (this.setList[i] === lastItem) {
            this.setList.splice(index, 1);
          }
        }
      }
    },
    removeSet(index) {
      this.setList.push(this.orderSetName[index]);
      this.orderSetName.splice(index, 1);
      this.orderSetNum.splice(index, 1);
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

.sureButton {
  margin-top: 10px;
  position: relative;
  left: 50%;
  transform: translate(-50%);
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
img{
  /*设置图片宽度和浏览器宽度一致*/
  width:100%;
  height:inherit;
}
</style>