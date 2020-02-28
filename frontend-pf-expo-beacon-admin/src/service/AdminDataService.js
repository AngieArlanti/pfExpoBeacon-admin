import axios from 'axios'

const ADMIN_API_URL = 'http://localhost:8085/admin'

class AdminDataService {

    retrieveAllStands() {
        return axios.get(`${ADMIN_API_URL}/stands`,
        {
            headers: {
            Authorization: "Basic " + window.btoa("admin:admin")
          }
        });
    }

    deleteStand(id) {
        return axios.get(`${ADMIN_API_URL}/stand/delete/${id}`,
        {
           headers: {
            Authorization: "Basic " + window.btoa("admin:admin")
            }
        });
    }
}

export default new AdminDataService()