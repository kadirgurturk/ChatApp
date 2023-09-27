import axios from "axios";


const MESSAGE_URL = "chat/"


class MessageService {
    
    sendMessage(messageRequest)
    {
        return axios.post(MESSAGE_URL + "send" , chatRequest, {
            headers: {
              'Authorization': localStorage.getItem("tokenKey")
            }
          })
    }
   
}

export default new MessageService();