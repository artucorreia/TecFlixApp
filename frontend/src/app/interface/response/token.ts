export interface Token {
    userId: string;
    accessToken: string;  
    refreshToken: string;
    createdAt: Date;
    expiresAt: Date;
}
