import axios, { AxiosInstance } from "axios";

class ApiConfig {

    private baseURL     = 'http://61.2.136.192:8096';//san
    private basepepURL  = 'http://192.168.1.65:8097';//pep
    private baseSecURL  = 'http://192.168.1.65:8098';//cms
    private baseamlURL  = 'http://192.168.1.44:8093';//aml
    private basebtmsURL = 'http://192.168.1.62:8082';//btms

    private apiBaseUrl: string;
    private apiBaseSecUrl: string;
    private apibasepepURL: string;
    private apibaseamlURL: string;
    private apibasebtmsURL: string;


    constructor() {
        this.apiBaseUrl = this.baseURL;

        this.apiBaseSecUrl = this.baseSecURL;
        this.apibasepepURL=this.basepepURL;
        this.apibaseamlURL=this.baseamlURL;
        this.apibasebtmsURL=this.basebtmsURL;
    }

    private getApiBaseURL = () => {
        return this.apiBaseUrl;
    }

    public getAxiosInstance = () => {
        return axios.create({
            baseURL: this.getApiBaseURL()
        });
    }

    private getApiBaseSecURL = () => {
        return this.apiBaseSecUrl;
    }

    public getAxiosSecInstance = () => {
        return axios.create({ baseURL: this.getApiBaseSecURL() });
    }

    private getApiPepBaseSecURL = () => {
        return this.apibasepepURL;
    }

    public getAxiosThrirdInstance = () => {
        return axios.create({ baseURL: this.getApiPepBaseSecURL() });
    }
    private getApiAmlBaseSecURL = () => {
        return this.apibaseamlURL;
    }

    public getAxiosFourInstance = () => {
        return axios.create({ baseURL: this.getApiAmlBaseSecURL() });
    }
    private getApiBtmsBaseSecURL = () => {
        return this.apibasebtmsURL;
    }

    public getAxiosFifthInstance = () => {
        return axios.create({ baseURL: this.getApiBtmsBaseSecURL() });
    }
}

export default ApiConfig;