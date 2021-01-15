package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class back {
    private Database db = new Database();
    private customer cu = db.make_cu();
    private administrator ad = db.make_ad();
    private query qu = db.make_qu();
    int id_set;
    int id_cu;

    @CrossOrigin
    @RequestMapping(value = "/regist", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int Regist(@RequestBody Map<String, String> putin) throws Exception {
        System.out.println(putin.get("username" + " " + putin.get("password")));
        int ans = cu.Regist(putin.get("username"),putin.get("password"));
        return ans;
    }

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int Login(@RequestBody Map<String, String> putin) throws Exception {
        System.out.println(putin.get("userType") + putin.get("password") + putin.get("username"));
        int ans;
        if (putin.get("userType").equals("1")) {
            ans = cu.Login(putin.get("username"),putin.get("password")).get(0);
            id_cu = cu.Login(putin.get("username"),putin.get("password")).get(1);
        }
        else ans = ad.Login(putin.get("username"),putin.get("password"));
        return ans;
    }

    @CrossOrigin
    @RequestMapping(value = "/newRestaurant", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int addrestaurant(@RequestBody Map<String, String> putin) throws Exception {
        int ans = ad.addrestaurant(putin.get("location"),putin.get("volume"),putin.get("telephone"));
        return ans;
    }

    @CrossOrigin
    @RequestMapping(value = "/queryRestaurant", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<String> queryRestaurant(@RequestBody Map<String, String> putin) throws Exception {
        return qu.queryRestaurant();
    }

    @CrossOrigin
    @RequestMapping(value = "/modifyRestaurant", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int modifyrestaurant(@RequestBody Map<String, String> putin) throws Exception {
        System.out.println(putin.get("location")+putin.get("volume")+putin.get("telephone"));
        int ans = ad.modifyrestaurant(putin.get("location"),putin.get("volume"),putin.get("telephone"));
        return ans;
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteRestaurant", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int deleterestaurant(@RequestBody Map<String, String> putin) throws Exception {
        System.out.println(putin.get("location"));
        return ad.deleterestaurant(putin.get("location"));
    }

    @CrossOrigin
    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int addemployee(@RequestBody Map<String, String> putin) throws Exception {
        return ad.addemployee(putin.get("name"),putin.get("age"),putin.get("position"),putin.get("telephone"));
    }

    @CrossOrigin
    @RequestMapping(value = "/queryEmployee", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<String> queryEmployee(@RequestBody Map<String, String> putin) throws Exception {
        return qu.queryEmployee();
    }

    @CrossOrigin
    @RequestMapping(value = "/modifyEmployee", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int modifyemployee(@RequestBody Map<String, String> putin) throws Exception {
        String[] idName = putin.get("idName").split(":");
        return ad.modifyemployee(Integer.valueOf(idName[0]),idName[1],putin.get("age"),putin.get("position").split(":")[0],putin.get("telephone"));
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int deleteemployee(@RequestBody Map<String, String> putin) throws Exception {
        String[] idName = putin.get("idName").split(":");
        return ad.deleteemployee(Integer.valueOf(idName[0]));
    }

    @CrossOrigin
    @RequestMapping(value = "/addFood", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int addfood(@RequestBody Map<String, String> putin) throws Exception {
        return ad.addfood(putin.get("name"),Float.parseFloat(putin.get("cost")));
    }

    @CrossOrigin
    @RequestMapping(value = "/queryFood", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<String> queryFood(@RequestBody Map<String, String> putin) throws Exception {
        return qu.queryFood();
    }

    @CrossOrigin
    @RequestMapping(value = "/modifyFood", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int modifyfood(@RequestBody Map<String, String> putin) throws Exception {
        System.out.println(putin.get("foodName")+" "+Float.valueOf(putin.get("foodCost")));
        return ad.modifyfood(putin.get("foodName").split(":")[1],Float.valueOf(putin.get("foodCost")));
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteFood", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int deletefood(@RequestBody Map<String, String> putin) throws Exception {
        return ad.deletefood(putin.get("foodName").split(":")[1]);
    }

    @CrossOrigin
    @RequestMapping(value = "/addSet", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int addset_meal(@RequestBody Map<String, String> putin) throws Exception {
        List<Integer> ans = ad.addset_meal(putin.get("name"),Float.parseFloat(putin.get("cost")));
        id_set=ans.get(0);
        return ans.get(1);
    }

    @CrossOrigin
    @RequestMapping(value = "/setAddFood", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void addset_from(@RequestBody List<Map<String, String>> putin) throws Exception {
        for (Map<String ,String > food : putin) {
            String[] id = food.get("foodName").split(":");
            ad.addset_from(id_set,Integer.valueOf(id[0]),Integer.valueOf((food.get("foodNum"))));
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/modifySet", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int modifyset_meal(@RequestBody Map<String, String> putin) throws Exception {
        String[] id = putin.get("name").split(":");
        id_set=Integer.valueOf(id[0]);
        return ad.modifyset_meal(Integer.valueOf(id[0]),id[1],Integer.valueOf(putin.get("cost")));
    }

    @CrossOrigin
    @RequestMapping(value = "/querySet", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<String> querySet(@RequestBody Map<String, String> putin) throws Exception {
        return qu.querySet();
    }

    @CrossOrigin
    @RequestMapping(value = "/querySetFood", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String,List<String >> querySetFood(@RequestBody Map<String, String> putin) throws Exception {
        return qu.querySetFood(id_set);
    }

    @CrossOrigin
    @RequestMapping(value = "/queryOtherFood", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<String> queryOtherFood(@RequestBody Map<String, String> putin) throws Exception {
        return qu.queryOtherFood(id_set);
    }

    @CrossOrigin
    @RequestMapping(value = "/setModifyFood", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void modifyset_from(@RequestBody List<Map<String, String>> putin) throws Exception {
        for (Map<String ,String > food : putin) {
            String[] id = food.get("foodName").split(":");
            ad.modifyset_from(id_set,Integer.valueOf(id[0]),Integer.valueOf((food.get("foodNum"))));
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/setDeleteFood", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void deleteset_from(@RequestBody List<Map<String, String>> putin) throws Exception {
        for (Map<String ,String > food : putin) {
            String[] id = food.get("foodName").split(":");
            ad.deleteset_from(id_set,Integer.valueOf(id[0]));
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteSet", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void deleteset_meal(@RequestBody Map<String, String> putin) throws Exception {
            String[] id = putin.get("id").split(":");
            ad.deleteset_meal(Integer.valueOf(id[0]));
    }

    @CrossOrigin
    @RequestMapping(value = "/queryEmployeeIdName", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<String> queryEmployeeIdName(@RequestBody Map<String, String> putin) throws Exception {
        return qu.queryEmployeeIdName();
    }

    @CrossOrigin
    @RequestMapping(value = "/queryRestAddress", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<String> queryRestAddress(@RequestBody Map<String, String> putin) throws Exception {
        return qu.queryRestAddress();
    }

    @CrossOrigin
    @RequestMapping(value = "/addRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void addemployee_information(@RequestBody Map<String, String> putin) throws Exception {
        ad.addemployee_information(Integer.valueOf(putin.get("employeeIdName").split(":")[0]),Integer.valueOf(putin.get("restAddress").split(":")[0]),putin.get("entryTime").split(":")[0],putin.get("position"));
    }

    @CrossOrigin
    @RequestMapping(value = "/queryRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<String> queryRecord(@RequestBody Map<String, String> putin) throws Exception {
        return qu.queryRecord();
    }

    @CrossOrigin
    @RequestMapping(value = "/modifyRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void modifyemployee_information(@RequestBody Map<String, String> putin) throws Exception {
        System.out.println(Integer.valueOf(putin.get("record").split(":")[0]));
        ad.modifyemployee_information(Integer.valueOf(putin.get("record").split(":")[0]),Integer.valueOf(putin.get("record").split(":")[2]),putin.get("record").split(":")[4],putin.get("exitTime").split(":")[0]);
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void deleteemployee_information(@RequestBody Map<String, String> putin) throws Exception {
        ad.deleteemployee_information(Integer.valueOf(putin.get("record").split(":")[0]),Integer.valueOf(putin.get("record").split(":")[2]),putin.get("record").split(":")[4]);
    }

    @CrossOrigin
    @RequestMapping(value = "/queryAddress", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<String> queryAddress(@RequestBody Map<String, String> putin) throws Exception {
        return qu.queryAddress(id_cu);
    }

    @CrossOrigin
    @RequestMapping(value = "/orderBegin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void add_order_begin(@RequestBody Map<String, String> putin) throws Exception {
        cu.add_order_begin();
    }

    @CrossOrigin
    @RequestMapping(value = "/orderFood", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void add_foodorder(@RequestBody Map<String, String> putin) throws Exception {
        cu.add_foodorder(Integer.valueOf(putin.get("foodName").split(":")[0]),Integer.valueOf(putin.get("foodNum")));
    }

    @CrossOrigin
    @RequestMapping(value = "/orderSet", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void add_set_meal_order(@RequestBody Map<String, String> putin) throws Exception {
        cu.add_set_meal_order(Integer.valueOf(putin.get("setName").split(":")[0]),Integer.valueOf(putin.get("setNum")));
    }

    @CrossOrigin
    @RequestMapping(value = "/orderEnd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public float add_order_end(@RequestBody Map<String, String> putin) throws Exception {
        return cu.add_order_end(putin.get("restAddress"),Integer.valueOf(putin.get("address").split(":")[0]),putin.get("oderTime"));
    }

    @CrossOrigin
    @RequestMapping(value = "/queryOrderTime", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<String> queryOrderTime(@RequestBody Map<String, String> putin) throws Exception {
        return qu.queryOrderTime(id_cu);
    }

    @CrossOrigin
    @RequestMapping(value = "/feedBack", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void add_feedback(@RequestBody Map<String, String> putin) throws Exception {
        cu.add_feedback(id_cu,Integer.valueOf(putin.get("orderTime").split(":")[0]),putin.get("feedbackTime"),putin.get("feedback"));
    }

    @CrossOrigin
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void add_address_info(@RequestBody Map<String, String> putin) throws Exception {
        cu.add_address_info(putin.get("name"),putin.get("phone"),putin.get("address"));
    }

    @CrossOrigin
    @RequestMapping(value = "/modifyAddress", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void modify_address_info(@RequestBody Map<String, String> putin) throws Exception {
        cu.modify_address_info(Integer.valueOf(putin.get("id").split(":")[0]),putin.get("name"),putin.get("phone"),putin.get("address"));
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteAddress", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void delete_address_info(@RequestBody Map<String, String> putin) throws Exception {
        cu.delete_address_info(Integer.valueOf(putin.get("id").split(":")[0]));
    }

    @CrossOrigin
    @RequestMapping(value = "/superQueryFood", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String,List<String>> superQueryFood(@RequestBody Map<String, String> putin) throws Exception {
        Map<String,List<String>> ans = new HashMap<>();
        ans.put("food",qu.queryFood());
        ans.put("set",qu.querySet());
        ans.put("address",qu.queryAddress(id_cu));
        ans.put("restaurant",qu.queryRestAddress());
        return ans;
    }

    @CrossOrigin
    @RequestMapping(value = "/superOrderFood", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public float superOrderFood(@RequestBody Map<String, List<String>> putin) throws Exception {
        cu.add_order_begin();
        List<String> foodName = putin.get("foodName");
        List<String> foodNum = putin.get("foodNum");
        List<String> setName = putin.get("setName");
        List<String> setNum = putin.get("setNum");
        System.out.println(foodName.toString() + foodNum + setName + setNum);
        for (int i = 0;i < foodName.size();i++) {
            cu.add_foodorder(Integer.valueOf(foodName.get(i).split(":")[0]),Integer.valueOf(foodNum.get(i)));
        }
        for (int i=0;i < setName.size();i++) {
            cu.add_set_meal_order(Integer.valueOf(setName.get(i).split(":")[0]), Integer.valueOf(setNum.get(i)));
        }
        System.out.println(putin.get("orderTime").get(0));
        System.out.println(putin.get("restAddress").get(0).split(":")[1]);
        System.out.println(Integer.valueOf(putin.get("address").get(0).split(":")[0]));
        return cu.add_order_end(putin.get("restAddress").get(0).split(":")[1],Integer.valueOf(putin.get("address").get(0).split(":")[0]),putin.get("orderTime").get(0));
    }

    @CrossOrigin
    @RequestMapping(value = "/addPosition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int addPosition(@RequestBody Map<String, String> putin) throws Exception {
        return ad.addPosition(putin.get("positionName"),(putin.get("salary")));
    }

    @CrossOrigin
    @RequestMapping(value = "/modifyPosition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int modifyPosition(@RequestBody Map<String, String> putin) throws Exception {
        return ad.modifyPosition(putin.get("positionName").split(":")[0],(putin.get("salary")));
    }

    @CrossOrigin
    @RequestMapping(value = "/queryPosition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<String> queryPosition(@RequestBody Map<String, String> putin) throws Exception {
        return qu.queryPosition();
    }

    @CrossOrigin
    @RequestMapping(value = "/deletePosition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int deletePosition(@RequestBody Map<String, String> putin) throws Exception {
        return ad.deletePosition(putin.get("positionName").split(":")[0]);
    }

    @CrossOrigin
    @RequestMapping(value = "/superRecord", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String,List<String>> superRecord(@RequestBody Map<String, List<String>> putin) throws Exception {
        System.out.println("DCFVGBHNJMK<L>");
        Map<String,List<String>> ans = new HashMap<>();
        ans.put("restaurantList",qu.queryRestAddress());
        ans.put("employeeList",qu.queryEmployee());
        ans.put("positionList", qu.queryPosition());
        return ans;
    }

    @CrossOrigin
    @RequestMapping(value = "/superSet", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String,List<String>> superSet(@RequestBody Map<String, String> putin) throws Exception {
        Map<String,List<String>> ans = new HashMap<>();
        ans.put("otherFood",qu.queryOtherFood(Integer.valueOf(putin.get("id").split(":")[0])));
        ans.put("setFoodName",qu.querySetFood(Integer.valueOf(putin.get("id").split(":")[0])).get("foodName"));
        ans.put("setFoodNum",qu.querySetFood(Integer.valueOf(putin.get("id").split(":")[0])).get("foodNum"));
        return ans;
    }

    @CrossOrigin
    @RequestMapping(value = "/superModifySet", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void superModifySet(@RequestBody Map<String, List<String>> putin) throws Exception {
        int id_set=Integer.valueOf(putin.get("set").get(0).split(":")[0]);
        ad.modifyset_meal(id_set,putin.get("set").get(0).split(":")[1],Float.parseFloat(putin.get("cost").get(0)));
        List<String> x=putin.get("modifyFoodName");
        System.out.println(x);
        List<String> x1=putin.get("modifyFoodNum");
        for (int i=0;i< x.size();i++) {
            System.out.println(Integer.valueOf(x.get(i).split(":")[0]));
            ad.modifyset_from(id_set,Integer.valueOf(x.get(i).split(":")[0]),Integer.valueOf(x1.get(i)));
        }
        x=putin.get("addFoodName");
        x1=putin.get("addFoodNum");
        for (int i=0;i< x.size();i++) {
            ad.addset_from(id_set,Integer.valueOf(x.get(i).split(":")[0]),Integer.valueOf(x1.get(i)));
        }
        x=putin.get("deleteFoodName");
        for (int i=0;i< x.size();i++) {
            ad.deleteset_from(id_set,Integer.valueOf(x.get(i).split(":")[0]));
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/superAddSet", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int superAddSet(@RequestBody Map<String, List<String>> putin) throws Exception {
        List<Integer> ans =ad.addset_meal(putin.get("name").get(0),Float.parseFloat(putin.get("cost").get(0)));
        if (ans.get(1) != 1) {
            return 0;
        }
        int id_set=ans.get(0);
        List<String> x=putin.get("foodName");
        List<String> x1=putin.get("foodNum");
        for (int i=0;i< x.size();i++) {
            ad.addset_from(id_set,Integer.valueOf(x.get(i).split(":")[0]),Integer.valueOf(x1.get(i)));
        }
        return ans.get(1);
    }

    @CrossOrigin
    @RequestMapping(value = "/superEmployee", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String,List<String>> superEmployee(@RequestBody Map<String, List<String>> putin) throws Exception {
        System.out.println("GBHNJMK<L>?");
        Map<String,List<String>> ans = new HashMap<>();
        ans.put("name",qu.queryEmployee());
        ans.put("job",qu.queryJob());
        return ans;
    }
}
