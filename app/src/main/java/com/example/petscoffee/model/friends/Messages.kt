package com.example.petscoffee.model.friends

import java.io.Serializable

/**
 * description ： 好友留言消息类
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/1/29 20:12
 */

class Messages(val senderAccount: String, val recipientAccount: String, val content: String):Serializable