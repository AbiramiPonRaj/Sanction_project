// export interface CmsSearchData {
//   searchDtos: SearchDtos[];
// };

// export interface SearchDtos {
//   id: number;
//   searchId: number;
//   name: string;
//   matchingScore: number;
//   fromDate: string;
//   toDate: string;
// };

// export interface SearchDetailsDataList {
//   id: number;
//   searchId: number;
//   name: string;
//   searchingScore: number;
//   typeId: number;
//   uid: number;
//   fromDate: string;
//   toDate: string;
// };
export interface CmsSearchData {
  searchDtos: SearchDto[];
}

export interface SearchDto {

  name: string;
  searchingScore: number | null;
  uid: number;

  fromDate: string;
  toDate: string;
  searchDetailsDataList: SearchDetailsDataList[];
}

export interface SearchDetailsDataList {

  searchId: number;
  name: string;
  searchingScore: number;
  uid: number;

  fromDate: string;
  toDate: string;
}
