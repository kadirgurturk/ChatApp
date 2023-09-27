import axios from "axios";


const CHAT_URL = "chat/"


class ChatService {
    
    createChat(chatRequest)
    {
        return axios.post(CHAT_URL + "single" , chatRequest, {
            headers: {
              'Authorization': localStorage.getItem("tokenKey")
            }
          })
    }

    getUserAllChatList(userid)
    {
        return axios.get(CHAT_URL + "single/user/?userid="+userid , {
            headers: {
              'Authorization': localStorage.getItem("tokenKey")
            }
          })
    }

    getChatById(chatId)
    {
        return axios.get(CHAT_URL + "single/?chatid="+userid , {
            headers: {
              'Authorization': localStorage.getItem("tokenKey")
            }
          })
    }

   
}

export default new ChatService();