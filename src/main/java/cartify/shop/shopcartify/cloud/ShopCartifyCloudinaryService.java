package cartify.shop.shopcartify.cloud;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import lombok.AllArgsConstructor;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class ShopCartifyCloudinaryService implements CloudService{
//    private final AppConfig appConfig;

    @Override
    public String upload(MultipartFile file)  {
        Cloudinary cloudinary = new Cloudinary();
        Uploader uploader = cloudinary.uploader();
        try{
            Map<?,?> response = uploader.upload(file.getBytes(), ObjectUtils.asMap(
//                    "public_id","promiscuous/users/media/"+file.getName(),
//                    "api_key",appConfig.getCloudApiKey(),
//                    "api_secret",appConfig.getCloudSecret(),
//                    "cloud_name",appConfig.getCloudName(),
//                    "secure",true,
//                    "resource_type", "auto"
            ));

            return response.get("url").toString();
        }catch (IOException exception){
            throw new RuntimeException("File upload failed");
        }

    }
}
