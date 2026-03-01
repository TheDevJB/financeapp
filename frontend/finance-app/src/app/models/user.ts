export interface User {
    userId?: number;
    firstName: string;
    lastName: string;
    email: string;
    phone: string;
    username: string;
    password?: string;
}

export interface LoginRequest {
    username: string;
    password: string;
}
