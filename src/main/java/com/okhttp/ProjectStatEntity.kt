package main.java.com.okhttp

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import java.io.IOException
import java.text.DateFormat

/**
 * Created by chenfeiyue on 17/8/25.
 * Description ：自动投标详情
 */
class ProjectStatEntity {

    /**
     * {
    "eventType": "",
    "userId": "1943447976",
    "appId": "com.yinker.android",
    "appVer": "4.9.5",
    "os": "Android Nexus 5 5.1.1",
    "deviceId": "12345678901234567890",
    "netEnv": "WIFI",
    "sysTime": "2017-11-13 23:05:00:990",
    "timeDiff": "20000",
    "params": {
    "url": "http://ykapptest2.jiandollar.net/app/usercenter/yeepay/invest?userid=1943447976&token=8C964D4897767188224CB9737E646537&amount=1080.0&campaignsID=1943458655&loanId=252392284&platform=android&version=3.1.2"
    }
    }
     */

    var eventType: String? = null
    var userId: String? = null
    var appId: String? = null
    var appVer: String? = null
    var os: String? = null
    var deviceId: String? = null
    var netEnv: String? = null
    var sysTime: String? = null
    var timeDiff: Long = 0

    var params: Map<String, Any>? = null

    fun setExtraParams(extraParams: Map<String, Any>?) {
        this.params = extraParams
    }

    companion object {
        @JvmStatic fun build(): ProjectStatEntity {
            val entity = ProjectStatEntity()
//            val authData = ContentHandler.getInstance().authData
//            if (authData != null) {
//                entity.userId = authData.userId
//            }
//            entity.appId = YKAppUtil.getPkgName(MyYKApplication.getInstance().applicationContext)
//            entity.appVer = YKAppUtil.getVersionName(MyYKApplication.getInstance().applicationContext)
//            entity.os = YKDeviceUtil.getStatisticsInfo()
//            entity.deviceId = YKDeviceUtil.getDeviceId()
//            entity.netEnv = YKDeviceUtil.getCurrentNetworkType(MyYKApplication.getInstance().applicationContext)
            entity.appId = "123"
            entity.appVer = "123"
            entity.sysTime = DateFormat.getDateTimeInstance().format(System.currentTimeMillis())
//            entity.timeDiff = SharedPreference.getLong(SharedPreFile.YKAndroid, SharedPreKey.DIFF_TIME, 0L)
            return entity
        }

        @JvmStatic fun build(eventType: String, extraParams: Map<String, Any>?): ProjectStatEntity {
            val entity = build()
            entity.eventType = eventType
            entity.setExtraParams(extraParams)
            return entity
        }


        @Throws(IOException::class)
        @JvmStatic fun main(args: Array<String>) {
            val params: MutableMap<String, Any> = mutableMapOf(Pair("PVG", "浦东"), Pair("SHA", "虹桥"), Pair("HGH", "萧山"))
            val entity = build("input", params)
            val bookA = RedBagInfo(1L, "20", 1)
            val bookB = RedBagInfo(2L, "21", 3)
            val list = listOf<RedBagInfo>(bookA, bookB)
            params.put("redBagInfo", bookA.toJSONObject())
            val jsonObject1 = Gson().toJson(entity)
            val jsonObject2 = GsonBuilder().create().toJson(entity)
            System.out.println(jsonObject1)
            System.out.println(jsonObject2)
        }


    }


    /**
     * 红包信息
     */
    class RedBagInfo constructor(ID: Long, name: String?, awardType: Int) {
        val ID = ID
        val name = name
        val awardType = awardType
        override fun toString(): String {
            return "RedBagInfo(ID=$ID, ruleName=$name, awardType=$awardType)"
        }

        fun toJSONObject(): JsonObject {
            val jsonObject = JsonObject()
            jsonObject.addProperty("ID", ID)
            jsonObject.addProperty("name", name)
            jsonObject.addProperty("awardType", awardType)
            return jsonObject
        }
    }
}


