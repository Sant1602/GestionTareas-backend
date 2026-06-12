export interface boards{
        Id: number,
        code: string,
        name: string,
        description: string
        isActive: boolean,
        ProjectId: number
}

export interface projects{
        id: number,
        code: string,
        name: string,
        description: string,
        isActive: boolean
}

export interface ProjectResponse{
    projects: projects[];
    amount: number;
};

// export interface UserInfo {
//     id: number;
//     username: string;
//     name: string;
//     last_name: string;
//     second_last_name: string;
//     password: string;
//     edad: number;
//     email: string;
//     date: string;
//     updateDate: string;
//     roleId: number;
// }

export interface UserInfo {
    name: string;
    lastName: string;
    role: number;
}
