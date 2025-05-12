import { Role } from '../roles/role.enum';
export declare class LoginDto {
    readonly email: string;
    readonly password: string;
    readonly role: Role;
}
