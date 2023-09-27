import axios from "axios";


const USER_URL = "user/"


class UserService {
    
    searchUser(query)
    {
        return axios.get(CHAT_URL+ "?query" , {
            headers: {
              'Authorization': localStorage.getItem("tokenKey")
            }
          })
    }
   
}

export default new UserService();