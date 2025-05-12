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
exports.MentorshipRequestController = void 0;
const common_1 = require("@nestjs/common");
const mentorship_request_service_1 = require("./mentorship-request.service");
const mentorship_request_dto_1 = require("./dto/mentorship-request.dto");
const roles_decorator_1 = require("../auth/decorators/roles.decorator");
const role_enum_1 = require("../auth/roles/role.enum");
const update_status_dto_1 = require("./dto/update-status.dto");
let MentorshipRequestController = class MentorshipRequestController {
    constructor(mentorshipRequestService) {
        this.mentorshipRequestService = mentorshipRequestService;
    }
    async create(dto, authHeader) {
        const token = authHeader.replace('Bearer ', '');
        return this.mentorshipRequestService.createMentorshipRequest(dto, token);
    }
    async findAll(authHeader) {
        const token = authHeader.replace('Bearer ', '');
        return this.mentorshipRequestService.getAllRequests(token);
    }
    async findAllMentees(authHeader) {
        const token = authHeader.replace('Bearer ', '');
        return this.mentorshipRequestService.getAllRequestsSendByMentees(token);
    }
    async findOne(id) {
        return this.mentorshipRequestService.getRequestById(id);
    }
    async update(id, dto) {
        return this.mentorshipRequestService.updateRequest(id, dto);
    }
    async remove(id) {
        return this.mentorshipRequestService.deleteRequest(id);
    }
    async setRequestStatus(id, updateStatusDto) {
        const { status } = updateStatusDto;
        return this.mentorshipRequestService.setRequestStatus(id, status);
    }
};
exports.MentorshipRequestController = MentorshipRequestController;
__decorate([
    (0, common_1.Post)(),
    (0, roles_decorator_1.Roles)(role_enum_1.Role.MENTEE),
    __param(0, (0, common_1.Body)()),
    __param(1, (0, common_1.Headers)('authorization')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [mentorship_request_dto_1.CreateMentorshipRequestDto, String]),
    __metadata("design:returntype", Promise)
], MentorshipRequestController.prototype, "create", null);
__decorate([
    (0, common_1.Get)(),
    (0, roles_decorator_1.Roles)(role_enum_1.Role.MENTEE),
    __param(0, (0, common_1.Headers)('authorization')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String]),
    __metadata("design:returntype", Promise)
], MentorshipRequestController.prototype, "findAll", null);
__decorate([
    (0, common_1.Get)('/mentors/sent'),
    (0, roles_decorator_1.Roles)(role_enum_1.Role.MENTOR),
    __param(0, (0, common_1.Headers)('authorization')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String]),
    __metadata("design:returntype", Promise)
], MentorshipRequestController.prototype, "findAllMentees", null);
__decorate([
    (0, common_1.Get)('/:id'),
    (0, roles_decorator_1.Roles)(role_enum_1.Role.MENTEE),
    __param(0, (0, common_1.Param)('id')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String]),
    __metadata("design:returntype", Promise)
], MentorshipRequestController.prototype, "findOne", null);
__decorate([
    (0, common_1.Patch)('/:id'),
    (0, roles_decorator_1.Roles)(role_enum_1.Role.MENTEE),
    __param(0, (0, common_1.Param)('id')),
    __param(1, (0, common_1.Body)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String, Object]),
    __metadata("design:returntype", Promise)
], MentorshipRequestController.prototype, "update", null);
__decorate([
    (0, common_1.Delete)('/:id'),
    (0, roles_decorator_1.Roles)(role_enum_1.Role.MENTEE),
    __param(0, (0, common_1.Param)('id')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String]),
    __metadata("design:returntype", Promise)
], MentorshipRequestController.prototype, "remove", null);
__decorate([
    (0, common_1.Patch)('/status/change/:id'),
    (0, roles_decorator_1.Roles)(role_enum_1.Role.MENTOR),
    __param(0, (0, common_1.Param)('id')),
    __param(1, (0, common_1.Body)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String, update_status_dto_1.UpdateStatusDto]),
    __metadata("design:returntype", Promise)
], MentorshipRequestController.prototype, "setRequestStatus", null);
exports.MentorshipRequestController = MentorshipRequestController = __decorate([
    (0, common_1.Controller)('mentorship-requests'),
    __metadata("design:paramtypes", [mentorship_request_service_1.MentorshipRequestService])
], MentorshipRequestController);
//# sourceMappingURL=mentorship-request.controller.js.map