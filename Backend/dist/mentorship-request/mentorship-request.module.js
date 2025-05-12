"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.MentorshipRequestModule = void 0;
const common_1 = require("@nestjs/common");
const mongoose_1 = require("@nestjs/mongoose");
const mentorship_request_schema_1 = require("./schemas/mentorship-request.schema");
const mentorship_request_service_1 = require("./mentorship-request.service");
const mentorship_request_controller_1 = require("./mentorship-request.controller");
const token_service_1 = require("../auth/token.service");
const auth_module_1 = require("../auth/auth.module");
let MentorshipRequestModule = class MentorshipRequestModule {
};
exports.MentorshipRequestModule = MentorshipRequestModule;
exports.MentorshipRequestModule = MentorshipRequestModule = __decorate([
    (0, common_1.Module)({
        imports: [
            mongoose_1.MongooseModule.forFeature([
                { name: mentorship_request_schema_1.MentorshipRequest.name, schema: mentorship_request_schema_1.MentorshipRequestSchema },
            ]),
            auth_module_1.AuthModule,
        ],
        controllers: [mentorship_request_controller_1.MentorshipRequestController],
        providers: [mentorship_request_service_1.MentorshipRequestService, token_service_1.TokenExtractionService],
    })
], MentorshipRequestModule);
//# sourceMappingURL=mentorship-request.module.js.map