import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from "@/views/Login.vue";
import Register from "@/views/Register.vue";
import Customer from "@/views/Customer/Customer.vue";
import Administrator from "@/views/administrator/Administrator.vue";
import NewRestaurant from "@/views/administrator/restaurant/NewRestaurant.vue";
import ModifyRestaurant from "@/views/administrator/restaurant/ModifyRestaurant.vue";
import DeleteRestaurant from "@/views/administrator/restaurant/DeleteRestaurant.vue";
import AddEmployee from "@/views/administrator/employee/AddEmployee";
import ModifyEmployee from "@/views/administrator/employee/ModifyEmployee";
import DeleteEmployee from "@/views/administrator/employee/DeleteEmployee";
import AddFood from "@/views/administrator/food/AddFood";
import ModifyFood from "@/views/administrator/food/ModifyFood";
import DeleteFood from "@/views/administrator/food/DeleteFood";
import AddSet from "@/views/administrator/set/AddSet";
import ModifySet from "@/views/administrator/set/ModifySet";
import DeleteSet from "@/views/administrator/set/DeleteSet";
import Order from "@/views/Customer/Order";
import Feedback from "@/views/Customer/Feedback";
import DeleteAddress from "@/views/Customer/DeleteAddress";
import AddAddress from "@/views/Customer/AddAddress";
import ModifyAddress from "@/views/Customer/ModifyAddress";
import AddRecord from "@/views/administrator/record/AddRecord";
import DeleteRecord from "@/views/administrator/record/DeleteRecord";
import ModifyRecord from "@/views/administrator/record/ModifyRecord";
import AdminQueryEmployee from "@/views/administrator/employee/QueryEmployee";
import AdminQueryFood from "@/views/administrator/food/QueryFood";
import AdminQueryRecord from "@/views/administrator/record/QueryRecord";
import AdminQueryRestaurant from "@/views/administrator/restaurant/QueryRestaurant";
import AdminQuerySet from "@/views/administrator/set/AdminQuerySet";
import AddPosition from "@/views/administrator/position/AddPosition";
import DeletePosition from "@/views/administrator/position/DeletePosition";
import ModifyPosition from "@/views/administrator/position/ModifyPosition";
import QueryPosition from "@/views/administrator/position/QueryPosition";

import QueryAddress from "@/views/Customer/QueryAddress";
import QueryOrder from "@/views/Customer/QueryOrder";
import QueryRestaurant from "@/views/Customer/QueryRestaurant";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    redirect: Login,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/customer',
    name: 'Customer',
    component: Customer,
    /*
    customer功能：
    order feedbackText
     */
    children: [
      {
        path: '/customer/order',
        name: '点餐',
        component: Order,
      },
      {
        path: '/customer/feedbackText',
        name: '评价',
        component: Feedback
      },
      {
        path: '/customer/addAddress',
        name: '添加取餐信息',
        component: AddAddress
      },
      {
        path: '/customer/modifyAddress',
        name: '修改取餐信息',
        component: ModifyAddress
      },
      {
        path: '/customer/DeleteAddress',
        name: '删除取餐信息',
        component: DeleteAddress
      },
      {
        path: '/customer/queryAddress',
        name: '查询取餐信息',
        component: QueryAddress
      },
      {
        path: '/customer/queryOrder',
        name: '查询订单',
        component: QueryOrder
      },
      {
        path: '/customer/queryRestaurant',
        name: '查询餐厅',
        component: QueryRestaurant
      },

    ]
  },
  {
    path: '/administrator',
    name: 'Administrator',
    component: Administrator,
    //redirect: '/modifyRestaurant',
    /*
    administrator功能：
    modifyRestaurant modifyRestaurant deleteRestaurant
    modifyEmployee modifyEmployee deleteEmployee
    modifyFood modifyFood deleteFood
    addSet modifySet deleteSet
    addMaterial modifyMaterial deleteMaterial
     */
    children: [
      {
        path: '/restaurantOperation/newRestaurant',
        name: '添加餐厅',
        component: NewRestaurant,
      },
      {
        path: '/restaurantOperation/modifyRestaurant',
        name: '修改餐厅信息',
        component: ModifyRestaurant,
      },
      {
        path: '/restaurantOperation/deleteRestaurant',
        name: '删除餐厅',
        component: DeleteRestaurant,
      },
      {
        path: '/restaurantOperation/queryRestaurant',
        name: '查询餐厅',
        component: AdminQueryRestaurant
      },

      {
        path: '/employeeOperation/addEmployee',
        name: '添加员工',
        component: AddEmployee,
      },
      {
        path: '/employeeOperation/modifyEmployee',
        name: '修改员工信息',
        component: ModifyEmployee,
      },
      {
        path: '/employeeOperation/deleteEmployee',
        name: '删除员工',
        component: DeleteEmployee,
      },
      {
        path: '/employeeOperation/queryPosition',
        name: '查询员工',
        component: AdminQueryEmployee,
      },

      {
        path: '/foodOperation/addFood',
        name: '添加食品',
        component: AddFood
      },
      {
        path: '/foodOperation/modifyFood',
        name: '修改食品',
        component: ModifyFood
      },
      {
        path: '/foodOperation/deleteFood',
        name: '删除食品',
        component: DeleteFood
      },
      {
        path: '/foodOperation/queryRecord',
        name: '查询食品',
        component: AdminQueryFood,
      },

      {
        path: '/setOperation/addSet',
        name: '添加套餐',
        component: AddSet
      },
      {
        path: '/setOperation/modifySet',
        name: '修改套餐',
        component: ModifySet
      },
      {
        path: '/setOperation/deleteSet',
        name: '删除套餐',
        component: DeleteSet
      },
      {
        path: '/setOperation/querySet',
        name: '查询套餐',
        component: AdminQuerySet
      },

      {
        path: '/recordOperation/addRecord',
        name: '添加入职记录',
        component: AddRecord
      },
      {
        path: '/recordOperation/modifyRecord',
        name: '修改入职记录',
        component: ModifyRecord
      },
      {
        path: '/recordOperation/deleteRecord',
        name: '删除入职记录',
        component: DeleteRecord
      },
      {
        path: '/recordOperation/queryRecord',
        name: '查询入职记录',
        component: AdminQueryRecord
      },

      {
        path: '/positionOperation/addPosition',
        name: '添加职位',
        component: AddPosition
      },
      {
        path: '/positionOperation/deletePosition',
        name: '删除职位',
        component: DeletePosition
      },
      {
        path: '/positionOperation/modifyPosition',
        name: '修改职位',
        component: ModifyPosition
      },
      {
        path: '/positionOperation/queryPosition',
        name: '查询职位',
        component: QueryPosition
      }

      // { //关于restaurant的操作分组
      //   path: '/restaurantOperation',
      //   foodName: '操作分组-餐厅',
      //   children: [
      //     {
      //       path: '/restaurantOperation/modifyRestaurant',
      //       foodName: '添加餐厅',
      //       component: NewRestaurant,
      //     },
      //     {
      //       path: '/restaurantOperation/modifyRestaurant',
      //       foodName: '修改餐厅信息',
      //       component: ModifyRestaurant,
      //     },
      //     {
      //       path: '/restaurantOperation/deleteRestaurant',
      //       foodName: '删除餐厅',
      //       component: DeleteRestaurant,
      //     },
      //   ]
      // },
      // { //关于employee的操作分组
      //   path: '/employeeOperation',
      //   foodName: '操作分组-员工',
      //   children: [
      //     {
      //       path: '/employeeOperation/modifyEmployee',
      //       foodName: '添加员工',
      //       component: AddEmployee,
      //     },
      //     {
      //       path: '/employeeOperation/modifyEmployee',
      //       foodName: '修改员工信息',
      //       component: ModifyEmployee,
      //     },
      //     {
      //       path: '/employeeOperation/deleteEmployee',
      //       foodName: '删除员工',
      //       component: DeleteEmployee,
      //     },
      //   ]
      // },
      // { //关于food的操作分组
      //   path: '/foodOperation',
      //   foodName: '操作分组-食品',
      //   children: [
      //     {
      //       path: '/foodOperation/modifyFood',
      //       foodName: '添加食品',
      //       component: AddFood
      //     },
      //     {
      //       path: '/foodOperation/modifyFood',
      //       foodName: '修改食品',
      //       component: ModifyFood
      //     },
      //     {
      //       path: '/foodOperation/deleteFood',
      //       foodName: '删除食品',
      //       component: DeleteFood
      //     },
      //   ]
      // },
      // { //关于set的操作分组
      //   path: 'setOperation',
      //   foodName: '操作分组-套餐',
      //   children: [
      //     {
      //       path: 'setOperation/addSet',
      //       foodName: '添加套餐',
      //       component: AddSet
      //     },
      //     {
      //       path: 'setOperation/modifySet',
      //       foodName: '修改套餐',
      //       component: ModifySet
      //     },
      //     {
      //       path: 'setOperation/deleteSet',
      //       foodName: '删除套餐',
      //       component: DeleteSet
      //     },
      //   ]
      // },
      // {
      //   path: '/materialOperation',
      //   foodName: '操作分组-原材料',
      //   children: [
      //     { //关于material的操作分组
      //       path: '/materialOperation/addMaterial',
      //       foodName: '添加原材料',
      //       component: AddMaterial
      //     },
      //     {
      //       path: '/materialOperation/modifyMaterial',
      //       foodName: '修改原材料',
      //       component: ModifyMaterial
      //     },
      //     {
      //       path: '/materialOperation/deleteMaterial',
      //       foodName: '删除原材料',
      //       component: DeleteMaterial
      //     },
      //   ]
      // }
    ]
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
