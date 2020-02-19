import axios from 'axios'

const ADMIN_API_URL = 'http://localhost:8080/admin'

class AdminDataService {

    retrieveAllStands(name) {
        return axios.get(`${ADMIN_API_URL}/stands`,
        { headers: {
            Authorization: "Basic " + window.btoa("admin:admin")
          }
        });
    }
}

export default new AdminDataService()