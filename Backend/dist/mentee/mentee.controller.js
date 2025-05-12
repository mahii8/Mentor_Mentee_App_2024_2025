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
exports.MenteeController = void 0;
const common_1 = require("@nestjs/common");
const mentee_service_1 = require("./mentee.service");
const roles_decorator_1 = require("../auth/decorators/roles.decorator");
const role_enum_1 = require("../auth/roles/role.enum");
let MenteeController = class MenteeController {
    constructor(menteeService) {
        this.menteeService = menteeService;
    }
    getAllMentees() {
        return this.menteeService.findAllMentees();
    }
};
exports.MenteeController = MenteeController;
__decorate([
    (0, roles_decorator_1.Roles)(role_enum_1.Role.MENTOR),
    (0, common_1.Get)(),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", []),
    __metadata("design:returntype", void 0)
], MenteeController.prototype, "getAllMentees", null);
exports.MenteeController = MenteeController = __decorate([
    (0, common_1.Controller)('mentees'),
    __metadata("design:paramtypes", [mentee_service_1.MenteeService])
], MenteeController);
//# sourceMappingURL=mentee.controller.js.map