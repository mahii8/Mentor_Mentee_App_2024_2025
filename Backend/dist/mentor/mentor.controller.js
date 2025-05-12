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
exports.MentorController = void 0;
const common_1 = require("@nestjs/common");
const mentor_service_1 = require("./mentor.service");
const role_enum_1 = require("../auth/roles/role.enum");
const roles_decorator_1 = require("../auth/decorators/roles.decorator");
let MentorController = class MentorController {
    constructor(mentorService) {
        this.mentorService = mentorService;
    }
    getAllMentors() {
        return this.mentorService.findAllMentors();
    }
};
exports.MentorController = MentorController;
__decorate([
    (0, roles_decorator_1.Roles)(role_enum_1.Role.MENTEE),
    (0, common_1.Get)(),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", []),
    __metadata("design:returntype", void 0)
], MentorController.prototype, "getAllMentors", null);
exports.MentorController = MentorController = __decorate([
    (0, common_1.Controller)('mentors'),
    __metadata("design:paramtypes", [mentor_service_1.MentorService])
], MentorController);
//# sourceMappingURL=mentor.controller.js.map