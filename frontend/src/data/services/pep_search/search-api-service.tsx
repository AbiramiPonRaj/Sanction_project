import HttpClientWrapper from "../../api/http-client-wrapper";
import { SearchPayload } from "./search-payload";

class SearchApiService {

    private httpClientWrapper: HttpClientWrapper;

    constructor() {
        this.httpClientWrapper = new HttpClientWrapper();
    };

    CreateSearch = async (payload: SearchPayload) => {
        try {
            const response = await this.httpClientWrapper.ALpost3('/api/v1/search/createSearch', payload);
            const data = response.data;
            return data;
        } catch (error) {
            throw error;
        }
    };

    getSearch = async () => {
        try {
            const response = await this.httpClientWrapper.ALget3('/api/v1/search');
            return response;
        } catch (error) {
            throw error;
        }
    };

    getSearchs = async () => {
        try {
            const levelId = 1;
            const response = await this.httpClientWrapper.ALget3(`/api/v1/Search/RecordsCount?name=Parthiban%20N%20G&matching_score=90&uid=0`);
            return response;
        } catch (error) {
            throw error;
        }
    };

    updateSearch = async (id: number, payload: SearchPayload) => {
        try {
            const response = await this.httpClientWrapper.put(`/api/v1/search/${id}`, payload);
            const data = response.data;
            return data;
        } catch (error) {
            throw error;
        }
    };

    blockSearch = async (id: number) => {
        try {
            const response = await this.httpClientWrapper.put(`/api/v1/search/{id}/${id}/block`);
            const data = response.data;
            return data;
        } catch (error) {
            throw error;
        }
    };

    unblockSearch = async (id: number) => {
        try {
            const response = await this.httpClientWrapper.put(`/api/v1/search/{id}/${id}/unblock`);
            const data = response.data;
            return data;
        } catch (error) {
            throw error;
        }
    };

}

export default SearchApiService;