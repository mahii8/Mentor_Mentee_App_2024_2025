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
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.MentorshipRequestService = void 0;
const common_1 = require("@nestjs/common");
const mongoose_1 = require("@nestjs/mongoose");
const mongoose_2 = require("mongoose");
const mentorship_request_schema_1 = require("./schemas/mentorship-request.schema");
const status_enum_1 = require("./enums/status.enum");
const token_service_1 = require("../auth/token.service");
let MentorshipRequestService = class MentorshipRequestService {
    constructor(mentorshipRequestModel, tokenService) {
        this.mentorshipRequestModel = mentorshipRequestModel;
        this.tokenService = tokenService;
    }
    async setRequestStatus(id, status) {
        const updatedRequest = await this.mentorshipRequestModel.findByIdAndUpdate(id, { status }, { new: true });
        if (!updatedRequest) {
            throw new common_1.NotFoundException(`Mentorship request with ID ${id} not found`);
        }
        return updatedRequest;
    }
    async createMentorshipRequest(dto, token) {
        const menteeId = this.tokenService.extractUserId(token);
        const request = {
            ...dto,
            status: status_enum_1.Status.PENDING,
            mentorId: new mongoose_2.Types.ObjectId(dto.mentorId),
            menteeId: new mongoose_2.Types.ObjectId(menteeId),
        };
        const newRequest = new this.mentorshipRequestModel(request);
        return await newRequest.save();
    }
    async getAllRequests(token) {
        const userId = this.tokenService.extractUserId(token);
        const menteeObjectId = new mongoose_2.Types.ObjectId(userId);
        return await this.mentorshipRequestModel
            .find({ menteeId: menteeObjectId })
            .exec();
    }
    async getAllRequestsSendByMentees(token) {
        const userId = this.tokenService.extractUserId(token);
        const mentorObjectId = new mongoose_2.Types.ObjectId(userId);
        const mentees = await this.mentorshipRequestModel
            .find({ mentorId: mentorObjectId })
            .exec();
        return mentees;
    }
    async getRequestById(id) {
        return await this.mentorshipRequestModel.findById(id).exec();
    }
    async deleteRequest(id) {
        const result = await this.mentorshipRequestModel
            .deleteOne({ _id: id })
            .exec();
        return { deleted: result.deletedCount > 0 };
    }
    async updateRequest(id, dto) {
        return await this.mentorshipRequestModel
            .findByIdAndUpdate(id, dto, { new: true })
            .exec();
    }
};
exports.MentorshipRequestService = MentorshipRequestService;
exports.MentorshipRequestService = MentorshipRequestService = __decorate([
    (0, common_1.Injectable)(),
    __param(0, (0, mongoose_1.InjectModel)(mentorship_request_schema_1.MentorshipRequest.name)),
    __metadata("design:paramtypes", [mongoose_2.Model,
        token_service_1.TokenExtractionService])
], MentorshipRequestService);
//# sourceMappingURL=mentorship-request.service.js.map