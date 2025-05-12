"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.TokenExtractionService = void 0;
const common_1 = require("@nestjs/common");
const jwt_1 = require("@nestjs/jwt");
let TokenExtractionService = class TokenExtractionService {
    constructor(jwtService) {
        this.jwtService = jwtService;
    }
    extractEmail(token) {
        try {
            const decoded = this.jwtService.decode(token);
            if (!decoded || !decoded.email) {
                throw new common_1.UnauthorizedException('Email not found in token');
            }
            return decoded.email;
        }
        catch (error) {
            throw new common_1.UnauthorizedException('Invalid token');
        }
    }
    extractUserId(token) {
        try {
            const decoded = this.jwtService.decode(token);
            if (!decoded || !decoded.userId) {
                throw new common_1.UnauthorizedException('User ID not found in token');
            }
            return decoded.userId;
        }
        catch (error) {
            throw new common_1.UnauthorizedException('Invalid token');
        }
    }
};
exports.TokenExtractionService = TokenExtractionService;
exports.TokenExtractionService = TokenExtractionService = __decorate([
    (0, common_1.Injectable)(),
    __metadata("design:paramtypes", [jwt_1.JwtService])
], TokenExtractionService);
//# sourceMappingURL=token.service.js.map