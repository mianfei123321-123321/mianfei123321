import {BASE_RESOURCES_URL} from "@/config/constants";

export function useResource() {
    // relativePath结构类似 resources/avatar/20240601/123.jpg
    function toAddressable(relativePath:string){
        if (relativePath) {
            return BASE_RESOURCES_URL + relativePath
        }
        return relativePath
    }
    return {toAddressable}
}