import { JwtService } from '@nestjs/jwt';
export declare class TokenExtractionService {
    private readonly jwtService;
    constructor(jwtService: JwtService);
    extractEmail(token: string): string;
    extractUserId(token: string): string;
}
