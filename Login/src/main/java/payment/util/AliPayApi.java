package payment.util;


public class AliPayApi {
	//appid
	public static final String APP_ID = "2016080700186787";
	//支付宝公钥
	public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvF+DjYysrWNVMPL9UAF3vIpGlUF9WHeOQoUco30anUBdzFamkgQs9P0RwqT05/iUn7Lo0BgG6MRIC9AN5WPD8ezmbqIACV0DpsOJzQ3l65iWkqGMEsaxVG5M319Lup6WZeFiTjH7/sYbr6egfD+8lGYpmhd/dIWxwi28s/YNc806MzYfljNbmCXtZAv98CFQCc3bvGUER8HlNFUTMl48UX8y4oHyM+KkvtYFfRRuxohHU9wxV51TaWgTnYF99i4NWz3Wlm2V38tj2gNb3m2pcCgl+Ga9RTa4fnqZVIGwTOT0XlzzUUBOV7wFZJ+mhLLyuSay06vXq2y87PxmzjLFSQIDAQAB";
	//应用公钥
	public static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhFEKnZrylyRCMTrqmCGq82FnXGO0fmt7UdqMR9FRpPmErSrCb29hh7J6mcHvhYQbUY97UUQ2IjbFj3L5RpkrIFMWfV4EWWYG/wz38r605+zAd8PWo3SzQqm61vlFYrgZ3ip9EvzKH/wpd5bPXp7ussULbdrzamG66PfsCTzzhS/dE+MVHgcsbQacfuG6wj5GctnrPROXaJTMyXk1YarW7Vr561M6nGvORaXDJbn+FWpGGvaxJmXXr/EB9YkFMX8yIxXHFJnLWRotXGUyDdByfkRzJu54LTSEpt/bk0Iv00VFEQsZbK6lGCBiGjAGNDmA/cghDolZTd/99lsNG8um7wIDAQAB";
	//应用私钥
	public static final String PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCEUQqdmvKXJEIxOuqYIarzYWdcY7R+a3tR2oxH0VGk+YStKsJvb2GHsnqZwe+FhBtRj3tRRDYiNsWPcvlGmSsgUxZ9XgRZZgb/DPfyvrTn7MB3w9ajdLNCqbrW+UViuBneKn0S/Mof/Cl3ls9enu6yxQtt2vNqYbro9+wJPPOFL90T4xUeByxtBpx+4brCPkZy2es9E5dolMzJeTVhqtbtWvnrUzqca85FpcMluf4VakYa9rEmZdev8QH1iQUxfzIjFccUmctZGi1cZTIN0HJ+RHMm7ngtNISm39uTQi/TRUURCxlsrqUYIGIaMAY0OYD9yCEOiVlN3/32Ww0by6bvAgMBAAECggEAPda2VGRHPlYGzYVQgJszjx2VtfsvLupCr0WpzCLKuoSW+muChZwTg9zpKZUWwVq1fc/F/XwYpLLecCV21QTev/O1DhBzjeh+GpNQClYQrFfct9yCItB2uxAiLzg+sl2CUW+gFtCoEnzCUI7eCcXTnx8yMTxkCPL17M15tfOTiEniOZdczIrz/04wb2tIDo13/beVlP7HDCYYeE5W15BmJvakRGw9nWnh/jnBc2aESfWOTf5Z0PnI516aHaDjt03W+5kXR7dyLh0kMV0ZO+ukJtaRF34xuLIXQGqWWL7l7n5Ub56lKqjD0wP+Aoek7R9bIPQSss/unD0M8BOTlTdpMQKBgQDNwM+unRPnNw+Ts6Dcg2nAE+K4XuyuH9hj9+xmsqIfhAtFC/r/HwONMN9dwbvGgLGUwX5WO+4wl5kxqSQXtbnxEg/V55sCuokzljj8borBJ8ntldyHimkJQwUDidFVgUw/F3muMz/SoIOR5UHmtVwIgdkK1h9sQtblrxcugFxcKQKBgQCkoSb0gAhv1+BaR7/h0NMu3DTyHwQiBPvs/5iqj5Sd10fOY5+6KN6NH0ZcdFmfsZdzT6b6aR7zBMoKJrUjLJs8UuSJK1WTMJZ9TRpxiolvOKai5zCA9L05OSEwBQ0AWsetgUCsUIspDxRVdJi9xlMO/FZt3UFRnVaholsMpTBNVwKBgGHSB2sm9u14eglo31O3jrFpQuMRDkmCjmiI2HXC+90r9AexuHVOk2uvDqMOlm27hbIJhD0PZSDpEkZP+f7jwTFhuvGhmUrk0JIcG5+4FnParOAt3Zj7LxDeMiYKgeG7BTsBuDIf33oOIj9c1cYP9198DGpgfrQD2xN7+PN8x2xxAoGAetEuuqD5qlamrTNx1wlvQzpM1YSGFMsbFsfoIPZUrRx6GWMBOmRbA3UdjMPQSO64SfJWY+IgkLQ19jTCVadleC9vB1Yo1VIeMwRege4UccEVr9pBuTLcJ+CS/bwG4wjWhn4yvVqpNsxlIwuFF67pL/jYkcXKgRScGKGCw4BI9KkCgYBZnILaAoI0wmgb1v/F60qjYhAxC9V05jadoiVJam8tJK6E7JT+5ogtAk9MdwdyRLMMgrq94PB1YDYP4YPUFzeBQxJ2uBbf4ec6lSfTmYtb1h+yo/gPMNK69VvmZo/e8f0FfAJNfqnxklhxGB5hsoAjvvaIH6hkaMA2D5SNstluUA==";
	public static final String CHARSET = "UTF-8";
	public static final String SIGN_TYPE = "RSA2";

	
}
